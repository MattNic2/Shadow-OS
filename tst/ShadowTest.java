import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class ShadowTest {
    // tests that RAM and CPU are empty
    @Test
    public void test1(){
        Shadow shadow = new Shadow();
        Assert.assertTrue(shadow.getRAM().isEmpty());
        Assert.assertTrue(shadow.getCPU().isEmpty());
    }

    // tests encryption
    @Test
    public void test2(){
        Shadow shadow = new Shadow();
        shadow.getRAM().add("hello");
        shadow.loadProgram();
        List<String> cpu = shadow.getCPU();
        Assert.assertTrue(cpu.get(0).equals("jgnnq"));
    }

    // tests decryption
    @Test
    public void test3() {
        Shadow shadow = new Shadow();
        shadow.getCPU().add("jgnnq");
        shadow.refresh();
        List<String> ram = shadow.getRAM();
        Assert.assertTrue(ram.get(0).equals("hello"));
    }

    // tests that exit clears
    @Test
    public void test4() throws FileNotFoundException {
        Shadow shadow = new Shadow();
        // starts empty
        Assert.assertTrue(shadow.getRAM().isEmpty());
        Assert.assertTrue(shadow.getCPU().isEmpty());

        //add text to RAM and CPU
        shadow.getRAM().add("hello");
        shadow.loadProgram();

        // verifies that text was added
        Assert.assertFalse(shadow.getRAM().isEmpty());
        Assert.assertFalse(shadow.getCPU().isEmpty());

        shadow.clearOS();

        // verifies that text was deleted
        Assert.assertTrue(shadow.getRAM().isEmpty());
        Assert.assertTrue(shadow.getCPU().isEmpty());
    }
}
