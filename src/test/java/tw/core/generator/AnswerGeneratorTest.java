package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;

import static junit.framework.TestCase.assertNotNull;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {


    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() {
        randomIntGenerator =new RandomIntGenerator();
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void should_not_throw_exception_when_call_generator() throws OutOfRangeAnswerException {
        answerGenerator.generate();
    }

    @Test
    public void should_return_an_answer_when_call_generator() throws OutOfRangeAnswerException {
        assertNotNull("answer is not null",answerGenerator.generate().toString());
    }


}

