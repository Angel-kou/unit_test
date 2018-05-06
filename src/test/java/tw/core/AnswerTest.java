package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void should_return_an_answer_when_input_a_string() {
        assertEquals("1 2 3 4",Answer.createAnswer("1 2 3 4").toString());
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_exception_when_answer_format_is_incorrect() throws OutOfRangeAnswerException {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","3"));
        answer.validate();
    }

    @Test
    public void should_not_exist_exception_when_string_is_validate() throws OutOfRangeAnswerException {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        answer.validate();
    }


    @Test
    public void should_return_a_record_used_for_showing_result_when_user_input_an_answer() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));

        Record record = answer.check(inputAnswer);

        assertNotNull("record is not null",record);
        assertEquals(4,record.getValue()[0]);
        assertEquals(0,record.getValue()[1]);
    }

    @Test
    public void should_return_index_of_element_when_input_an_exist_element() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals(1,answer.getIndexOfNum("2"));
    }

    @Test
    public void should_return_negative_one_when_element_is_not_exist() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals(-1,answer.getIndexOfNum("5"));
    }

    @Test
    public void should_return_a_string_with_character_is_elements_of_numlist_when_answer_convert_to_string() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals("1 2 3 4",answer.toString());
    }

}