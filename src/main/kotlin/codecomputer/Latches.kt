package codecomputer

/**
 * This interface add a [bar] member to the [Readable] interface, intended to represent an output which negates the
 * `Readable`'s normal output.
 */
interface Invertible : Readable {
    /**
     * A [Readable] which should always hold the opposite state of this [Readable]
     *
     * This represents various Q-bar outputs seen in chapters fourteen and seventeen of *Code*
     */
    val bar: Readable
}

/**
 * This class provides shared functionality for classes which implement [Invertible].
 */
abstract class RelayBar private constructor(protected val delegate: Signal) : Readable by delegate, Invertible {
    constructor(state: Boolean = false) : this(Relay(state))

    override abstract val bar: Readable
}

/**
 * A `FlipFlop` has an internal state which is `true` when the `s` input is `true`, `false` when the `r` input is `true`,
 * and unchanged when both inputs are `false`. The behavior is undefined if both inputs are `true`. The initial state may
 * optionally be provided at construction time.
 *
 * This class represents the *R-S flip-flop* described on page 162 of *Code*
 */
class FlipFlop(r: Readable, s: Readable, state: Boolean = false) : RelayBar(state) {
    override val bar: Readable

    init {
        val bar = Relay(!state)
        delegate.link(bar nor r)
        bar.link(delegate nor s)
        this.bar = bar
    }
}

/**
 * A `LevelLatch` has an internal state which matches `data` when `clock` is `true`, and remains unchanged when `clock`
 * is true. The `clear` and `preset` inputs override that behavior and act as `r` and `s` inputs of the [FlipFlop] class.
 * The initial state may optionally be provided at construction time.
 *
 * This class represents the *level-triggered D-type flip-flop* described on page 166 of *Code*.
 *
 * @see MultiLevelLatch
 */
class LevelLatch(clock: Readable, data: Readable, state: Boolean = false) :
        Invertible by FlipFlop(clock and !data, clock and data, state)

/**
 * An `EdgeLatch` has an internal state which changes to match `data` when `clock` moves from the `false` state to the
 * `true` state. The `clear` and `preset` inputs override that behavior and act as `r` and `s` inputs of the [FlipFlop] class.
 * The initial state may optionally be provided at construction time.
 *
 * This class represents the *edge-triggered D-type flip-flop with preset and clear* described on page 178 of *Code*.
 *
 * @see MultiEdgeLatch
 */
class EdgeLatch(clock: Readable, data: Readable, clear: Readable = Constant.FALSE, preset: Readable = Constant.FALSE,
                state: Boolean = false) : RelayBar(state) {
    override val bar: Readable

    init {
        val bar = Relay(!state)
        val relay1 = Relay(!state)
        val relay2 = Relay(state)

        val flipFlop2 = FlipFlop(relay1 or !clock, preset or data)
        val flipFlop1 = FlipFlop(clear or flipFlop2.bar, preset or !clock)
        val flipFlop3 = FlipFlop(clear or relay1, preset or relay2)

        relay1.link(flipFlop1.bar)
        relay2.link(flipFlop2)
        delegate.link(flipFlop3)
        bar.link(flipFlop3.bar)
        this.bar = bar
    }
}

/**
 * A `MultiLevelLatch` chains together multiple [LevelLatch]s which share a `clock` input.
 *
 * This class represents the multiple-bit latch described on page 167 of *code*
 *
 * @see LevelLatch
 */
class MultiLevelLatch(clock: Readable, data: List<Readable>, state: Int = 0) :
        List<Invertible> by (data.indices.map { LevelLatch(clock, data[it], state.nthBit(it)) })

/**
 * A `MultiEdgeLatch` chains together multiple [EdgeLatch]s which share a `clock` input.
 *
 * This class represents the edge-triggered equivalent of [MultiLevelLatch]
 *
 * @see EdgeLatch
 */
class MultiEdgeLatch(clock: Readable, data: List<Readable>, clear: Readable = Constant.FALSE,
                     preset: List<Readable> = data.map { Constant.FALSE }, state: Int = 0) :
        List<Invertible> by (data.indices.map { EdgeLatch(clock, data[it], clear, preset[it], state.nthBit(it)) })

val List<Invertible>.bar: List<Readable> get() = map { it.bar }