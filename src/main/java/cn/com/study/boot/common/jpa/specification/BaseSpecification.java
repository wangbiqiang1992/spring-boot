package cn.com.study.boot.common.jpa.specification;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 抄自书中
 * 自定义的Specification的基类实现，没有包括日期类，还有各种其它操作符
 * (ps 拓展中)
 * @param <T>
 */
public class BaseSpecification<T> implements Specification<T> {

    /**
     * 属性分隔符
     */
    private static final String PROPERTY_SEPARATOR = ".";

    private List<Cnd> andConditions = new ArrayList<>();

    private List<Cnd> orConditions = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();

    public BaseSpecification<T> and(Cnd... conditions) {
        andConditions.addAll(Arrays.asList(conditions));
        return this;
    }

    public BaseSpecification<T> or(Cnd... conditions) {
        orConditions.addAll(Arrays.asList(conditions));
        return this;
    }

    public BaseSpecification<T> desc(String property) {
        this.orders.add(Order.desc(property));
        return this;
    }

    public BaseSpecification<T> asc(String property) {
        this.orders.add(Order.asc(property));
        return this;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate restrictions = cb.and(getAndPredicates(root, cb));
        restrictions = cb.and(restrictions, getOrPredicates(root, cb));
        query.orderBy(getOrders(root, cb));
        return restrictions;
    }

    private Predicate getAndPredicates(Root<T> root, CriteriaBuilder cb) {
        Predicate restrictions = cb.conjunction();
        for (Cnd condition : andConditions) {
            if (condition == null) {
                continue;
            }
            Path<?> path = this.getPath(root, condition.property);
            if (path == null) {
                continue;
            }
            switch (condition.operator) {
                case eq:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.and(restrictions, cb.equal(path, value));
                            } else {
                                restrictions = cb.and(restrictions, cb.equal(path, value));
                            }
                        }
                    }
                    break;
                case ne:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.and(restrictions, cb.notEqual(path, value));
                            } else {
                                restrictions = cb.and(restrictions, cb.notEqual(path, value));
                            }
                        }
                    }
                    break;
                case ge:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.and(restrictions, cb.ge((Path<Number>) path, value));
                    }
                    break;
                case gt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.and(restrictions, cb.gt((Path<Number>) path, value));
                    }
                    break;
                case lt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.and(restrictions, cb.lt((Path<Number>) path, value));
                    }
                    break;
                case le:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.and(restrictions, cb.le((Path<Number>) path, value));
                    }
                    break;
                case isNotNull:
                    restrictions = cb.and(restrictions, path.isNotNull());
                    break;
                case like:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.and(restrictions, cb.like((Expression<String>) path, "%" + value + "%"));
                            } else {
                                restrictions = cb.and(restrictions, cb.like((Expression<String>) path, "%null%"));
                            }
                        }
                    }
                    break;
            }
        }
        return restrictions;
    }

    private Predicate getOrPredicates(Root<T> root, CriteriaBuilder cb) {
        Predicate restrictions = cb.conjunction();
        for (Cnd condition : orConditions) {
            if (condition == null) {
                continue;
            }
            Path<?> path = this.getPath(root, condition.property);
            if (path == null) {
                continue;
            }
            switch (condition.operator) {
                case eq:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.or(restrictions, cb.equal(path, value));
                            } else {
                                restrictions = cb.or(restrictions, cb.equal(path, value));
                            }
                        }
                    }
                    break;
                case ne:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.or(restrictions, cb.notEqual(path, value));
                            } else {
                                restrictions = cb.or(restrictions, cb.notEqual(path, value));
                            }
                        }
                    }
                    break;
                case ge:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.or(restrictions, cb.ge((Path<Number>) path, value));
                    }
                    break;
                case gt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.or(restrictions, cb.gt((Path<Number>) path, value));
                    }
                    break;
                case lt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.or(restrictions, cb.lt((Path<Number>) path, value));
                    }
                    break;
                case le:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof Number) {
                        Number value = (Number) condition.value;
                        restrictions = cb.or(restrictions, cb.le((Path<Number>) path, value));
                    }
                    break;
                case isNotNull:
                    restrictions = cb.or(restrictions, path.isNotNull());
                    break;
                case like:
                    if (condition.value != null) {
                        if (String.class.isAssignableFrom(path.getJavaType()) && condition.value instanceof String) {
                            String value = (String) condition.value;
                            if (!value.isEmpty()) {
                                restrictions = cb.or(restrictions, cb.like((Expression<String>) path, "%" + value + "%"));
                            } else {
                                restrictions = cb.or(restrictions, cb.like((Expression<String>) path, "%null%"));
                            }
                        }
                    }
                    break;
            }
        }
        return restrictions;
    }

    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
    }

    /**
     * 排序
     *
     * @param root
     * @param cb
     * @return
     */
    private List<javax.persistence.criteria.Order> getOrders(Root<T> root, CriteriaBuilder cb) {
        List<javax.persistence.criteria.Order> orderList = new ArrayList<>();
        if (root == null || CollectionUtils.isEmpty(orders)) {
            return orderList;
        }
        for (Order order : orders) {
            if (order == null) {
                continue;
            }
            String property = order.getProperty();
            Sort.Direction direction = order.getDirection();
            Path<?> path = this.getPath(root, property);
            if (path == null || direction == null) {
                continue;
            }
            switch (direction) {
                case ASC:
                    orderList.add(cb.asc(path));
                    break;
                case DESC:
                    orderList.add(cb.desc(path));
                    break;
            }
        }
        return orderList;
    }


    @Getter
    public enum Operator {
        eq(" = "),
        ne(" != "),
        gt(" > "),
        lt(" < "),
        ge(" >= "),
        le(" <= "),
        like(" like "),
        isNotNull(" is not NULL ");

        private String operator;

        Operator(String operator) {
            this.operator = operator;
        }

    }

    public static class Cnd {

        String property;
        Operator operator;
        Object value;

        public Cnd(String property, Operator operator, Object value) {
            this.operator = operator;
            this.property = property;
            this.value = value;
        }

        public Cnd(String property, Operator operator) {
            this.operator = operator;
            this.property = property;
        }

        public static Cnd eq(String property, Object value) {
            return new Cnd(property, Operator.eq, value);
        }

        public static Cnd ne(String property, Object value) {
            return new Cnd(property, Operator.ne, value);
        }

        public static Cnd gt(String property, Object value) {
            return new Cnd(property, Operator.gt, value);
        }

        public static Cnd lt(String property, Object value) {
            return new Cnd(property, Operator.lt, value);
        }

        public static Cnd ge(String property, Object value) {
            return new Cnd(property, Operator.ge, value);
        }

        public static Cnd le(String property, Object value) {
            return new Cnd(property, Operator.le, value);
        }

        public static Cnd isNotNull(String property) {
            return new Cnd(property, Operator.isNotNull);
        }

        public static Cnd like(String property, Object value) {
            return new Cnd(property, Operator.like, value);
        }
    }

    @Data
    public static class Order {
        private String property;
        private Sort.Direction direction = Sort.Direction.ASC;

        public Order(String property, Sort.Direction direction) {
            this.property = property;
            this.direction = direction;
        }

        public static Order asc(String property) {
            return new Order(property, Sort.Direction.ASC);
        }

        public static Order desc(String property) {
            return new Order(property, Sort.Direction.DESC);
        }
    }

}
