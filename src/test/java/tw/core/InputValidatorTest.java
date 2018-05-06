package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Before
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void should_return_true_when_input_a_string_with_digit_number_equals_four_and_not_repeat() {
        assertTrue(inputValidator.validate("1 2 3 4"));
    }

    @Test
    public void should_return_true_when_input_a_string_with_digit_number_less_than_four() {
        assertFalse(inputValidator.validate("1 2 3"));
    }


    @Test
    public void should_return_true_when_input_a_string_with_digit_number_equals_four_and_contains_repeat_number() {
        assertFalse(inputValidator.validate("1 2 1 3"));
    }

    @Test
    public void should_return_true_when_input_a_string_with_digit_number_equals_four_but_character_no_less_than_ten() {
        assertFalse(inputValidator.validate("1 2 10 3"));
    }


}
