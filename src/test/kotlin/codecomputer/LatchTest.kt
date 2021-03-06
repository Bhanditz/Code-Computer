package codecomputer

import org.junit.Test

class LatchTest {
    @Test fun flipFlopTest() {
        val r = Relay()
        val s = Relay()
        val flipFlop = FlipFlop(r, s)
        flipFlop.readAndAssert(false, "flipFlop 1")
        flipFlop.bar.readAndAssert(true, "flipFlop bar 1")
        s.write(true)
        flipFlop.readAndAssert(true, "flipFlop 2")
        flipFlop.bar.readAndAssert(false, "flipFlop bar 2")
        s.write(false)
        flipFlop.readAndAssert(true, "flipFlop 3")
        flipFlop.bar.readAndAssert(false, "flipFlop bar 3")
        r.write(true)
        flipFlop.readAndAssert(false, "flipFlop 4")
        flipFlop.bar.readAndAssert(true, "flipFlop bar 4")
        r.write(false)
        flipFlop.readAndAssert(false, "flipFlop 5")
        flipFlop.bar.readAndAssert(true, "flipFlop bar 5")
    }

    @Test fun levelLatchTest() {
        val clock = Relay()
        val data = Relay()
        val clear = Relay()
        val preset = Relay()
        val latch = LevelLatch(clock, data, clear, preset)
        latch.readAndAssert(false, "latch 1")
        latch.bar.readAndAssert(true, "latch bar 1")
        data.write(true)
        latch.readAndAssert(false, "latch 2")
        latch.bar.readAndAssert(true, "latch bar 2")
        data.write(false)
        latch.readAndAssert(false, "latch 3")
        latch.bar.readAndAssert(true, "latch bar 3")
        clock.write(true)
        latch.readAndAssert(false, "latch 4")
        latch.bar.readAndAssert(true, "latch bar 4")
        data.write(true)
        latch.readAndAssert(true, "latch 5")
        latch.bar.readAndAssert(false, "latch bar 5")
        clock.write(false)
        latch.readAndAssert(true, "latch 6")
        latch.bar.readAndAssert(false, "latch bar 6")
        data.write(false)
        latch.readAndAssert(true, "latch 7")
        latch.bar.readAndAssert(false, "latch bar 7")
        clock.write(true)
        latch.readAndAssert(false, "latch 8")
        latch.bar.readAndAssert(true, "latch bar 8")
        clock.write(false)
        latch.readAndAssert(false, "latch 9")
        latch.bar.readAndAssert(true, "latch bar 9")
        data.write(true)
        latch.readAndAssert(false, "latch 10")
        latch.bar.readAndAssert(true, "latch bar 10")
        clock.write(true)
        latch.readAndAssert(true, "latch 11")
        latch.bar.readAndAssert(false, "latch bar 11")
        clock.write(false)
        latch.readAndAssert(true, "latch 12")
        latch.bar.readAndAssert(false, "latch bar 12")
        data.write(false)
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "latch 13")
        latch.bar.readAndAssert(true, "latch bar 13")
        clear.write(true)
        latch.readAndAssert(false, "latch 14")
        latch.bar.readAndAssert(true, "latch bar 14")
        data.write(true)
        latch.readAndAssert(false, "latch 15")
        latch.bar.readAndAssert(true, "latch bar 15")
        data.write(false)
        latch.readAndAssert(false, "latch 16")
        latch.bar.readAndAssert(true, "latch bar 16")
        data.write(true)
        latch.readAndAssert(false, "latch 17")
        latch.bar.readAndAssert(true, "latch bar 17")
        clock.write(true)
        latch.readAndAssert(false, "latch 18")
        latch.bar.readAndAssert(true, "latch bar 18")
        clock.write(false)
        latch.readAndAssert(false, "latch 19")
        latch.bar.readAndAssert(true, "latch bar 19")
        data.write(false)
        latch.readAndAssert(false, "latch 20")
        latch.bar.readAndAssert(true, "latch bar 20")
        clear.write(false)
        preset.write(true)
        latch.readAndAssert(true, "latch 21")
        latch.bar.readAndAssert(false, "latch bar 21")
        data.write(true)
        latch.readAndAssert(true, "latch 22")
        latch.bar.readAndAssert(false, "latch bar 22")
        data.write(false)
        latch.readAndAssert(true, "latch 23")
        latch.bar.readAndAssert(false, "latch bar 23")
        data.write(true)
        latch.readAndAssert(true, "latch 24")
        latch.bar.readAndAssert(false, "latch bar 24")
        clock.write(true)
        latch.readAndAssert(true, "latch 25")
        latch.bar.readAndAssert(false, "latch bar 25")
        clock.write(false)
        latch.readAndAssert(true, "latch 26")
        latch.bar.readAndAssert(false, "latch bar 26")
        data.write(false)
        latch.readAndAssert(true, "latch 27")
        latch.bar.readAndAssert(false, "latch bar 27")
        preset.write(false)
        clear.write(true)
        clear.write(false)
        latch.readAndAssert(false, "latch 28")
        latch.bar.readAndAssert(true, "latch bar 28")
        preset.write(true)
        preset.write(false)
        latch.readAndAssert(true, "latch 29")
        latch.bar.readAndAssert(false, "latch bar 29")
    }

    @Test fun edgeLatchTest() {
        val clock = Relay()
        val data = Relay()
        val clear = Relay()
        val preset = Relay()
        val latch = EdgeLatch(clock, data, clear, preset)

        latch.readAndAssert(false, "edge latch 1")
        latch.bar.readAndAssert(true, "edge latch bar 1")
        data.write(true)
        latch.readAndAssert(false, "edge latch 2")
        latch.bar.readAndAssert(true, "edge latch bar 2")
        clock.write(true)
        latch.readAndAssert(true, "edge latch 3")
        latch.bar.readAndAssert(false, "edge latch bar 3")
        data.write(false)
        latch.readAndAssert(true, "edge latch 4")
        latch.bar.readAndAssert(false, "edge latch bar 4")
        clock.write(false)
        latch.readAndAssert(true, "edge latch 5")
        latch.bar.readAndAssert(false, "edge latch bar 5")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "edge latch 6")
        latch.bar.readAndAssert(true, "edge latch bar 6")
        data.write(true)
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(true, "edge latch 7")
        latch.bar.readAndAssert(false, "edge latch bar 7")
        clock.write(true)
        data.write(false)
        clock.write(false)
        latch.readAndAssert(true, "edge latch 8")
        latch.bar.readAndAssert(false, "edge latch bar 8")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "edge latch 9")
        latch.bar.readAndAssert(true, "edge latch bar 9")
        data.write(true)
        clock.write(true)
        latch.readAndAssert(true, "edge latch 10")
        latch.bar.readAndAssert(false, "edge latch bar 10")
        clear.write(true)
        latch.readAndAssert(false, "edge latch 11")
        latch.bar.readAndAssert(true, "edge latch bar 11")
        clear.write(false)
        latch.readAndAssert(false, "edge latch 12")
        latch.bar.readAndAssert(true, "edge latch bar 12")
        data.write(false)
        clock.write(false)
        latch.readAndAssert(false, "edge latch 13")
        latch.bar.readAndAssert(true, "edge latch bar 13")
        preset.write(true)
        latch.readAndAssert(true, "edge latch 14")
        latch.bar.readAndAssert(false, "edge latch bar 14")
        preset.write(false)
        latch.readAndAssert(true, "edge latch 15")
        latch.bar.readAndAssert(false, "edge latch bar 15")
    }

    @Test fun edgeLatchClearTest() {
        val clock = Relay()
        val data = Relay()
        val clear = Constant.TRUE
        val preset = Constant.FALSE
        val latch = EdgeLatch(clock, data, clear, preset)

        latch.readAndAssert(false, "clear edge latch 1")
        latch.bar.readAndAssert(true, "clear edge latch bar 1")
        data.write(true)
        latch.readAndAssert(false, "clear edge latch 2")
        latch.bar.readAndAssert(true, "clear edge latch bar 2")
        clock.write(true)
        latch.readAndAssert(false, "clear edge latch 3")
        latch.bar.readAndAssert(true, "clear edge latch bar 3")
        data.write(false)
        latch.readAndAssert(false, "clear edge latch 4")
        latch.bar.readAndAssert(true, "clear edge latch bar 4")
        clock.write(false)
        latch.readAndAssert(false, "clear edge latch 5")
        latch.bar.readAndAssert(true, "clear edge latch bar 5")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "clear edge latch 6")
        latch.bar.readAndAssert(true, "clear edge latch bar 6")
        data.write(true)
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "clear edge latch 7")
        latch.bar.readAndAssert(true, "clear edge latch bar 7")
        clock.write(true)
        data.write(false)
        clock.write(false)
        latch.readAndAssert(false, "clear edge latch 8")
        latch.bar.readAndAssert(true, "clear edge latch bar 8")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(false, "clear edge latch 9")
        latch.bar.readAndAssert(true, "clear edge latch bar 9")
    }

    @Test fun edgeLatchPresetTest() {
        val clock = Relay()
        val data = Relay()
        val clear = Constant.FALSE
        val preset = Constant.TRUE
        val latch = EdgeLatch(clock, data, clear, preset)

        latch.readAndAssert(true, "preset edge latch 1")
        latch.bar.readAndAssert(false, "preset edge latch bar 1")
        data.write(true)
        latch.readAndAssert(true, "preset edge latch 2")
        latch.bar.readAndAssert(false, "preset edge latch bar 2")
        clock.write(true)
        latch.readAndAssert(true, "preset edge latch 3")
        latch.bar.readAndAssert(false, "preset edge latch bar 3")
        data.write(false)
        latch.readAndAssert(true, "preset edge latch 4")
        latch.bar.readAndAssert(false, "preset edge latch bar 4")
        clock.write(false)
        latch.readAndAssert(true, "preset edge latch 5")
        latch.bar.readAndAssert(false, "preset edge latch bar 5")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(true, "preset edge latch 6")
        latch.bar.readAndAssert(false, "preset edge latch bar 6")
        data.write(true)
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(true, "preset edge latch 7")
        latch.bar.readAndAssert(false, "preset edge latch bar 7")
        clock.write(true)
        data.write(false)
        clock.write(false)
        latch.readAndAssert(true, "preset edge latch 8")
        latch.bar.readAndAssert(false, "preset edge latch bar 8")
        clock.write(true)
        clock.write(false)
        latch.readAndAssert(true, "preset edge latch 9")
        latch.bar.readAndAssert(false, "preset edge latch bar 9")
    }

    @Test fun multiLevelLatchTest() {
        val clock = Relay()
        val data = 0.toSignals(8)
        val clear = Relay()
        val preset = 0.toSignals(8)
        val latch = MultiLevelLatch(clock, data, clear, preset)
        latch.readAndAssert(0, "multilatch 1")
        data.write(25)
        latch.readAndAssert(0, "multilatch 2")
        clock.write(true)
        latch.readAndAssert(25, "multilatch 3")
        clock.write(false)
        latch.readAndAssert(25, "multilatch 4")
        data.write(87)
        latch.readAndAssert(25, "multilatch 5")
        clock.write(true)
        latch.readAndAssert(87, "multilatch 6")
        data.write(45)
        latch.readAndAssert(45, "multilatch 7")
        clear.write(true)
        latch.readAndAssert(0, "multilatch 8")
        clear.write(false)
        latch.readAndAssert(45, "multilatch 9")
        clock.write(false)
        clear.write(true)
        clear.write(false)
        latch.readAndAssert(0, "multilatch 10")
        preset.write(61)
        latch.readAndAssert(61, "multilatch 11")
        preset.write(0)
        latch.readAndAssert(61, "multilatch 12")

    }

    @Test fun multiEdgeLatchTest() {
        val clock = Relay()
        val data = 0.toSignals(8)
        val clear = Relay()
        val preset = 0.toSignals(8)
        val latch = MultiEdgeLatch(clock, data, clear, preset)
        latch.readAndAssert(0, "multilatch 1")
        data.write(25)
        latch.readAndAssert(0, "multilatch 2")
        clock.write(true)
        latch.readAndAssert(25, "multilatch 3")
        clock.write(false)
        latch.readAndAssert(25, "multilatch 4")
        data.write(87)
        latch.readAndAssert(25, "multilatch 5")
        clock.write(true)
        latch.readAndAssert(87, "multilatch 6")
        data.write(45)
        latch.readAndAssert(87, "multilatch 7")
        clear.write(true)
        latch.readAndAssert(0, "multilatch 8")
        clear.write(false)
        latch.readAndAssert(0, "multilatch 9")
        preset.write(255)
        latch.readAndAssert(255, "multilatch 10")
        preset.write(0)
        latch.readAndAssert(255, "multilatch 11")
    }

    @Test fun invertibleListTest() {
        val invertibles = 1.toBits(2).map {
            object : RelayBar(it) {
                override val bar = !delegate
            }
        }

        invertibles[0].readAndAssert(true, "invertibles normal")
        invertibles[1].readAndAssert(false, "invertibles normal")
        invertibles.bar[0].readAndAssert(false, "invertibles bar")
        invertibles.bar[1].readAndAssert(true, "invertibles bar")
    }
}
