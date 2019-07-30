package com.payProject.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ResourcesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResourcesExample() {
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

        public Criteria andResourcesidIsNull() {
            addCriterion("resourcesId is null");
            return (Criteria) this;
        }

        public Criteria andResourcesidIsNotNull() {
            addCriterion("resourcesId is not null");
            return (Criteria) this;
        }

        public Criteria andResourcesidEqualTo(Integer value) {
            addCriterion("resourcesId =", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidNotEqualTo(Integer value) {
            addCriterion("resourcesId <>", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidGreaterThan(Integer value) {
            addCriterion("resourcesId >", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidGreaterThanOrEqualTo(Integer value) {
            addCriterion("resourcesId >=", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidLessThan(Integer value) {
            addCriterion("resourcesId <", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidLessThanOrEqualTo(Integer value) {
            addCriterion("resourcesId <=", value, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidIn(List<Integer> values) {
            addCriterion("resourcesId in", values, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidNotIn(List<Integer> values) {
            addCriterion("resourcesId not in", values, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidBetween(Integer value1, Integer value2) {
            addCriterion("resourcesId between", value1, value2, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesidNotBetween(Integer value1, Integer value2) {
            addCriterion("resourcesId not between", value1, value2, "resourcesid");
            return (Criteria) this;
        }

        public Criteria andResourcesnameIsNull() {
            addCriterion("resourcesName is null");
            return (Criteria) this;
        }

        public Criteria andResourcesnameIsNotNull() {
            addCriterion("resourcesName is not null");
            return (Criteria) this;
        }

        public Criteria andResourcesnameEqualTo(String value) {
            addCriterion("resourcesName =", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameNotEqualTo(String value) {
            addCriterion("resourcesName <>", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameGreaterThan(String value) {
            addCriterion("resourcesName >", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameGreaterThanOrEqualTo(String value) {
            addCriterion("resourcesName >=", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameLessThan(String value) {
            addCriterion("resourcesName <", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameLessThanOrEqualTo(String value) {
            addCriterion("resourcesName <=", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameLike(String value) {
            addCriterion("resourcesName like", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameNotLike(String value) {
            addCriterion("resourcesName not like", value, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameIn(List<String> values) {
            addCriterion("resourcesName in", values, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameNotIn(List<String> values) {
            addCriterion("resourcesName not in", values, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameBetween(String value1, String value2) {
            addCriterion("resourcesName between", value1, value2, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcesnameNotBetween(String value1, String value2) {
            addCriterion("resourcesName not between", value1, value2, "resourcesname");
            return (Criteria) this;
        }

        public Criteria andResourcestypeIsNull() {
            addCriterion("resourcesType is null");
            return (Criteria) this;
        }

        public Criteria andResourcestypeIsNotNull() {
            addCriterion("resourcesType is not null");
            return (Criteria) this;
        }

        public Criteria andResourcestypeEqualTo(Integer value) {
            addCriterion("resourcesType =", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeNotEqualTo(Integer value) {
            addCriterion("resourcesType <>", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeGreaterThan(Integer value) {
            addCriterion("resourcesType >", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("resourcesType >=", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeLessThan(Integer value) {
            addCriterion("resourcesType <", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeLessThanOrEqualTo(Integer value) {
            addCriterion("resourcesType <=", value, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeIn(List<Integer> values) {
            addCriterion("resourcesType in", values, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeNotIn(List<Integer> values) {
            addCriterion("resourcesType not in", values, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeBetween(Integer value1, Integer value2) {
            addCriterion("resourcesType between", value1, value2, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourcestypeNotBetween(Integer value1, Integer value2) {
            addCriterion("resourcesType not between", value1, value2, "resourcestype");
            return (Criteria) this;
        }

        public Criteria andResourceskeyIsNull() {
            addCriterion("resourcesKey is null");
            return (Criteria) this;
        }

        public Criteria andResourceskeyIsNotNull() {
            addCriterion("resourcesKey is not null");
            return (Criteria) this;
        }

        public Criteria andResourceskeyEqualTo(String value) {
            addCriterion("resourcesKey =", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyNotEqualTo(String value) {
            addCriterion("resourcesKey <>", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyGreaterThan(String value) {
            addCriterion("resourcesKey >", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyGreaterThanOrEqualTo(String value) {
            addCriterion("resourcesKey >=", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyLessThan(String value) {
            addCriterion("resourcesKey <", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyLessThanOrEqualTo(String value) {
            addCriterion("resourcesKey <=", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyLike(String value) {
            addCriterion("resourcesKey like", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyNotLike(String value) {
            addCriterion("resourcesKey not like", value, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyIn(List<String> values) {
            addCriterion("resourcesKey in", values, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyNotIn(List<String> values) {
            addCriterion("resourcesKey not in", values, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyBetween(String value1, String value2) {
            addCriterion("resourcesKey between", value1, value2, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourceskeyNotBetween(String value1, String value2) {
            addCriterion("resourcesKey not between", value1, value2, "resourceskey");
            return (Criteria) this;
        }

        public Criteria andResourcesurlIsNull() {
            addCriterion("resourcesUrl is null");
            return (Criteria) this;
        }

        public Criteria andResourcesurlIsNotNull() {
            addCriterion("resourcesUrl is not null");
            return (Criteria) this;
        }

        public Criteria andResourcesurlEqualTo(String value) {
            addCriterion("resourcesUrl =", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlNotEqualTo(String value) {
            addCriterion("resourcesUrl <>", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlGreaterThan(String value) {
            addCriterion("resourcesUrl >", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlGreaterThanOrEqualTo(String value) {
            addCriterion("resourcesUrl >=", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlLessThan(String value) {
            addCriterion("resourcesUrl <", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlLessThanOrEqualTo(String value) {
            addCriterion("resourcesUrl <=", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlLike(String value) {
            addCriterion("resourcesUrl like", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlNotLike(String value) {
            addCriterion("resourcesUrl not like", value, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlIn(List<String> values) {
            addCriterion("resourcesUrl in", values, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlNotIn(List<String> values) {
            addCriterion("resourcesUrl not in", values, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlBetween(String value1, String value2) {
            addCriterion("resourcesUrl between", value1, value2, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andResourcesurlNotBetween(String value1, String value2) {
            addCriterion("resourcesUrl not between", value1, value2, "resourcesurl");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeIsNull() {
            addCriterion("submitTime is null");
            return (Criteria) this;
        }

        public Criteria andSubmittimeIsNotNull() {
            addCriterion("submitTime is not null");
            return (Criteria) this;
        }

        public Criteria andSubmittimeEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime =", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime <>", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeGreaterThan(Date value) {
            addCriterionForJDBCDate("submitTime >", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime >=", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeLessThan(Date value) {
            addCriterionForJDBCDate("submitTime <", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime <=", value, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeIn(List<Date> values) {
            addCriterionForJDBCDate("submitTime in", values, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("submitTime not in", values, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitTime between", value1, value2, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmittimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitTime not between", value1, value2, "submittime");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIsNull() {
            addCriterion("submitSystem is null");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIsNotNull() {
            addCriterion("submitSystem is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemEqualTo(String value) {
            addCriterion("submitSystem =", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotEqualTo(String value) {
            addCriterion("submitSystem <>", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemGreaterThan(String value) {
            addCriterion("submitSystem >", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemGreaterThanOrEqualTo(String value) {
            addCriterion("submitSystem >=", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLessThan(String value) {
            addCriterion("submitSystem <", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLessThanOrEqualTo(String value) {
            addCriterion("submitSystem <=", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemLike(String value) {
            addCriterion("submitSystem like", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotLike(String value) {
            addCriterion("submitSystem not like", value, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemIn(List<String> values) {
            addCriterion("submitSystem in", values, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotIn(List<String> values) {
            addCriterion("submitSystem not in", values, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemBetween(String value1, String value2) {
            addCriterion("submitSystem between", value1, value2, "submitsystem");
            return (Criteria) this;
        }

        public Criteria andSubmitsystemNotBetween(String value1, String value2) {
            addCriterion("submitSystem not between", value1, value2, "submitsystem");
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