package com.vti.testing.specification;

import com.vti.testing.entity.Group;
import com.vti.testing.form.GroupForm.GroupFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class GroupSpecification {
    private static final String GROUP_NAME = "groupName";
    //    private static final String USERNAME = "username";
    private static final String CREATED_DATE = "createdDate";
    private static final String MIN_YEAR = "minYear";

    public static Specification<Group> buildWhere(GroupFilterForm form) {
        if (form == null) {
            return null;
        }
        Specification<Group> whereGrouptName = new SpecificationImpl(GROUP_NAME, form.getSearch());
//        Specification<Group> whereUsername = new SpecificationImpl(USERNAME, form.getSearch());
        Specification<Group> whereCreatedDate = new SpecificationImpl(CREATED_DATE, form.getCreatedDate());
        Specification<Group> whereMinYear = new SpecificationImpl(MIN_YEAR, form.getMinYear());
        return Specification.where(whereGrouptName).and(whereCreatedDate).and(whereMinYear);
    }

    @AllArgsConstructor
    public static class SpecificationImpl implements Specification<Group> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {
                case GROUP_NAME:
                    // name LIKE "%value%"
                    return criteriaBuilder.like(root.get("groupName"), "%" + value + "%");
//                case USERNAME:
//                    Join join = root.join("accounts", JoinType.LEFT);
//                    return query.distinct(true).where(criteriaBuilder.like(join.get("username"), "%" + value + "%")).getRestriction();
                case CREATED_DATE:
                    return criteriaBuilder.equal(root.get("createdDate").as(java.sql.Date.class), (Date) value);
                case MIN_YEAR:
                    // YEAR(created_date) >= value
                    return criteriaBuilder.greaterThanOrEqualTo(
                            criteriaBuilder.function("YEAR", Integer.class, root.get("createdDate")),
                            (Integer) value
                    );
            }
            return null;
        }
    }
}
