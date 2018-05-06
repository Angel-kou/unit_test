package tw.core;


import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import static junit.framework.TestCase.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setup() {
        randomIntGenerator = new RandomIntGenerator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_digitmax_is_smaller_than_numbers_of_need() {
        randomIntGenerator.generateNums(3,4);
    }

    @Test
    public void should_return_a_string_with_character_number_equals_numbers_of_need() {
        String generator = randomIntGenerator.generateNums(9,4);
        assertTrue(generator.split(" ").length==4);
    }

}