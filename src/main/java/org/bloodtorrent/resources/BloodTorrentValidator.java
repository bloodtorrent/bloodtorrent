package org.bloodtorrent.resources;

import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/1/13
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BloodTorrentValidator<T> {
    private Validator validator;

    protected BloodTorrentValidator()  {
        initValidator();
    }

    private void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public String getFirstValidationMessageAlphabetically(T vo) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(vo);
        List<String> messages = Lists.newArrayList();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messages.add(constraintViolation.getMessage());
        }
        Collections.sort(messages);
        return messages.get(0);
    }

    public boolean isInvalid(T vo) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(vo);
        return constraintViolations.size() > 0;
    }
}
