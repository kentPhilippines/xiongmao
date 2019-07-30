package com.payProject.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("roleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("roleId =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("roleId <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("roleId >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleId >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("roleId <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("roleId <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("roleId in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("roleId not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("roleId between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("roleId not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNull() {
            addCriterion("roleName is null");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNotNull() {
            addCriterion("roleName is not null");
            return (Criteria) this;
        }

        public Criteria andRolenameEqualTo(String value) {
            addCriterion("roleName =", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotEqualTo(String value) {
            addCriterion("roleName <>", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThan(String value) {
            addCriterion("roleName >", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThanOrEqualTo(String value) {
            addCriterion("roleName >=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThan(String value) {
            addCriterion("roleName <", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThanOrEqualTo(String value) {
            addCriterion("roleName <=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLike(String value) {
            addCriterion("roleName like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotLike(String value) {
            addCriterion("roleName not like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameIn(List<String> values) {
            addCriterion("roleName in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotIn(List<String> values) {
            addCriterion("roleName not in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameBetween(String value1, String value2) {
            addCriterion("roleName between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotBetween(String value1, String value2) {
            addCriterion("roleName not between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeIsNull() {
            addCriterion("roleCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeIsNotNull() {
            addCriterion("roleCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("roleCreateTime =", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("roleCreateTime <>", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("roleCreateTime >", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("roleCreateTime >=", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("roleCreateTime <", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("roleCreateTime <=", value, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("roleCreateTime in", values, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("roleCreateTime not in", values, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("roleCreateTime between", value1, value2, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolecreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("roleCreateTime not between", value1, value2, "rolecreatetime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeIsNull() {
            addCriterion("roleSubmitTime is null");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeIsNotNull() {
            addCriterion("roleSubmitTime is not null");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeEqualTo(Date value) {
            addCriterionForJDBCDate("roleSubmitTime =", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("roleSubmitTime <>", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeGreaterThan(Date value) {
            addCriterionForJDBCDate("roleSubmitTime >", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("roleSubmitTime >=", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeLessThan(Date value) {
            addCriterionForJDBCDate("roleSubmitTime <", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("roleSubmitTime <=", value, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeIn(List<Date> values) {
            addCriterionForJDBCDate("roleSubmitTime in", values, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("roleSubmitTime not in", values, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("roleSubmitTime between", value1, value2, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andRolesubmittimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("roleSubmitTime not between", value1, value2, "rolesubmittime");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIsNull() {
            addCriterion("SubmitSystem is null");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIsNotNull() {
            addCriterion("SubmitSystem is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemEqualTo(String value) {
            addCriterion("SubmitSystem =", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotEqualTo(String value) {
            addCriterion("SubmitSystem <>", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemGreaterThan(String value) {
            addCriterion("SubmitSystem >", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemGreaterThanOrEqualTo(String value) {
            addCriterion("SubmitSystem >=", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLessThan(String value) {
            addCriterion("SubmitSystem <", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLessThanOrEqualTo(String value) {
            addCriterion("SubmitSystem <=", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLike(String value) {
            addCriterion("SubmitSystem like", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotLike(String value) {
            addCriterion("SubmitSystem not like", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIn(List<String> values) {
            addCriterion("SubmitSystem in", values, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotIn(List<String> values) {
            addCriterion("SubmitSystem not in", values, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemBetween(String value1, String value2) {
            addCriterion("SubmitSystem between", value1, value2, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotBetween(String value1, String value2) {
            addCriterion("SubmitSystem not between", value1, value2, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}