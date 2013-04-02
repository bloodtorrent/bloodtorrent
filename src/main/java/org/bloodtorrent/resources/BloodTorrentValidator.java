package org.bloodtorrent.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.fest.util.Strings;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nullable;
import javax.validation.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/1/13
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BloodTorrentValidator<T> {
    private Validator validator;

    private String[] fieldNames;

    public void setFieldNames(String... fieldNames) {
        this.fieldNames = fieldNames;
    }


    protected BloodTorrentValidator()  {
        initValidator();
    }

    private void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public String getFirstValidationMessage(T vo) {
        ArrayList<ConstraintViolation<T>> violations = Lists.newArrayList(validator.validate(vo).iterator());

        if(fieldNames != null) {
            Collections.sort(violations,
                    new TypeOrdering<T>(NotBlank.class).compound(new DisplayedOrdering<T>()).compound(new TypeOrdering<T>(Pattern.class, Size.class)));
        } else {
            Collections.sort(violations, new TypeOrdering<T>(NotBlank.class, Pattern.class, Size.class));
        }

        return violations.get(0).getMessage();
    }

    public boolean isInvalid(T vo) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(vo);
        return constraintViolations.size() > 0;
    }


    private abstract class PrioritizedOrdering<T> extends Ordering<ConstraintViolation<T>> {
        @Override
        public int compare(@Nullable ConstraintViolation<T> violation1, @Nullable ConstraintViolation<T> violation2) {
            int priorityOfViolation1 = getPriority(violation1);
            int priorityOfViolation2 = getPriority(violation2);
            return Ints.compare(priorityOfViolation1, priorityOfViolation2);
        }

        protected abstract int getPriority(ConstraintViolation<T> violation);
    }

    private class TypeOrdering<T> extends PrioritizedOrdering<T> {
        private Class<?>[] annotations;

        TypeOrdering(Class<?>... annotations) {
            this.annotations = annotations;
        }

        protected int getPriority(ConstraintViolation<T> violation) {
            Class<?> thisType = violation.getConstraintDescriptor().getAnnotation().annotationType();
            for (int i = 0, TOTAL = annotations.length; i < TOTAL; i++) {
                if(thisType.equals(annotations[i]))
                    return i;
            }
            return Integer.MAX_VALUE;
        }
    }

    private class DisplayedOrdering<T> extends PrioritizedOrdering<T> {
        protected int getPriority(ConstraintViolation<T> violation) {
            final String fieldName = getFieldName(violation);
            for (int i = 0, TOTAL = fieldNames.length; i < TOTAL; i++) {
                if(fieldName.equals(fieldNames[i]))
                    return i;
            }
            return Integer.MAX_VALUE;
        }

        private String getFieldName(ConstraintViolation<T> violation) {
            for(Iterator<Path.Node> itr = violation.getPropertyPath().iterator(); itr.hasNext(); ) {
                String name = itr.next().getName();
                if(!Strings.isNullOrEmpty(name) && !"null".equals(name)) {
                    return name;
                }
            }
            return null;    // Should be avoided...
        }
    }
}
