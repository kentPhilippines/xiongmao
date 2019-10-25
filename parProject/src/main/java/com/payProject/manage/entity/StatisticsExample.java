package com.payProject.manage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StatisticsExample() {
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

        public Criteria andKeyIsNull() {
            addCriterion("`key` is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("`key` is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("`key` =", value, "key");
            return (Criteria) this;
        }
        public Criteria andKeyListEqualTo(List value) {
        	addCriterion("`key` in", value, "key");
        	return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("`key` <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("`key` >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`key` >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("`key` <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("`key` <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("`key` like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("`key` not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("`key` in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("key not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("`key` between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("`key` not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountSuIsNull() {
            addCriterion("amountSu is null");
            return (Criteria) this;
        }

        public Criteria andAmountSuIsNotNull() {
            addCriterion("amountSu is not null");
            return (Criteria) this;
        }

        public Criteria andAmountSuEqualTo(String value) {
            addCriterion("amountSu =", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuNotEqualTo(String value) {
            addCriterion("amountSu <>", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuGreaterThan(String value) {
            addCriterion("amountSu >", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuGreaterThanOrEqualTo(String value) {
            addCriterion("amountSu >=", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuLessThan(String value) {
            addCriterion("amountSu <", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuLessThanOrEqualTo(String value) {
            addCriterion("amountSu <=", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuLike(String value) {
            addCriterion("amountSu like", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuNotLike(String value) {
            addCriterion("amountSu not like", value, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuIn(List<String> values) {
            addCriterion("amountSu in", values, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuNotIn(List<String> values) {
            addCriterion("amountSu not in", values, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuBetween(String value1, String value2) {
            addCriterion("amountSu between", value1, value2, "amountSu");
            return (Criteria) this;
        }

        public Criteria andAmountSuNotBetween(String value1, String value2) {
            addCriterion("amountSu not between", value1, value2, "amountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountIsNull() {
            addCriterion("dealCount is null");
            return (Criteria) this;
        }

        public Criteria andDealCountIsNotNull() {
            addCriterion("dealCount is not null");
            return (Criteria) this;
        }

        public Criteria andDealCountEqualTo(String value) {
            addCriterion("dealCount =", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountNotEqualTo(String value) {
            addCriterion("dealCount <>", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountGreaterThan(String value) {
            addCriterion("dealCount >", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountGreaterThanOrEqualTo(String value) {
            addCriterion("dealCount >=", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountLessThan(String value) {
            addCriterion("dealCount <", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountLessThanOrEqualTo(String value) {
            addCriterion("dealCount <=", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountLike(String value) {
            addCriterion("dealCount like", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountNotLike(String value) {
            addCriterion("dealCount not like", value, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountIn(List<String> values) {
            addCriterion("dealCount in", values, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountNotIn(List<String> values) {
            addCriterion("dealCount not in", values, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountBetween(String value1, String value2) {
            addCriterion("dealCount between", value1, value2, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountNotBetween(String value1, String value2) {
            addCriterion("dealCount not between", value1, value2, "dealCount");
            return (Criteria) this;
        }

        public Criteria andDealCountSuIsNull() {
            addCriterion("dealCountSu is null");
            return (Criteria) this;
        }

        public Criteria andDealCountSuIsNotNull() {
            addCriterion("dealCountSu is not null");
            return (Criteria) this;
        }

        public Criteria andDealCountSuEqualTo(String value) {
            addCriterion("dealCountSu =", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuNotEqualTo(String value) {
            addCriterion("dealCountSu <>", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuGreaterThan(String value) {
            addCriterion("dealCountSu >", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuGreaterThanOrEqualTo(String value) {
            addCriterion("dealCountSu >=", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuLessThan(String value) {
            addCriterion("dealCountSu <", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuLessThanOrEqualTo(String value) {
            addCriterion("dealCountSu <=", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuLike(String value) {
            addCriterion("dealCountSu like", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuNotLike(String value) {
            addCriterion("dealCountSu not like", value, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuIn(List<String> values) {
            addCriterion("dealCountSu in", values, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuNotIn(List<String> values) {
            addCriterion("dealCountSu not in", values, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuBetween(String value1, String value2) {
            addCriterion("dealCountSu between", value1, value2, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andDealCountSuNotBetween(String value1, String value2) {
            addCriterion("dealCountSu not between", value1, value2, "dealCountSu");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("`time` is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("`time` is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterionForJDBCDate("`time` =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`time` <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("`time` >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`time` >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterionForJDBCDate("`time` <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`time` <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterionForJDBCDate("`time` in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`time` not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`time` between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`time` not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andRetain1IsNull() {
            addCriterion("retain1 is null");
            return (Criteria) this;
        }

        public Criteria andRetain1IsNotNull() {
            addCriterion("retain1 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain1EqualTo(String value) {
            addCriterion("retain1 =", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1NotEqualTo(String value) {
            addCriterion("retain1 <>", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1GreaterThan(String value) {
            addCriterion("retain1 >", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1GreaterThanOrEqualTo(String value) {
            addCriterion("retain1 >=", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1LessThan(String value) {
            addCriterion("retain1 <", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1LessThanOrEqualTo(String value) {
            addCriterion("retain1 <=", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1Like(String value) {
            addCriterion("retain1 like", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1NotLike(String value) {
            addCriterion("retain1 not like", value, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1In(List<String> values) {
            addCriterion("retain1 in", values, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1NotIn(List<String> values) {
            addCriterion("retain1 not in", values, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1Between(String value1, String value2) {
            addCriterion("retain1 between", value1, value2, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain1NotBetween(String value1, String value2) {
            addCriterion("retain1 not between", value1, value2, "retain1");
            return (Criteria) this;
        }

        public Criteria andRetain2IsNull() {
            addCriterion("retain2 is null");
            return (Criteria) this;
        }

        public Criteria andRetain2IsNotNull() {
            addCriterion("retain2 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain2EqualTo(String value) {
            addCriterion("retain2 =", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2NotEqualTo(String value) {
            addCriterion("retain2 <>", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2GreaterThan(String value) {
            addCriterion("retain2 >", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2GreaterThanOrEqualTo(String value) {
            addCriterion("retain2 >=", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2LessThan(String value) {
            addCriterion("retain2 <", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2LessThanOrEqualTo(String value) {
            addCriterion("retain2 <=", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2Like(String value) {
            addCriterion("retain2 like", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2NotLike(String value) {
            addCriterion("retain2 not like", value, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2In(List<String> values) {
            addCriterion("retain2 in", values, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2NotIn(List<String> values) {
            addCriterion("retain2 not in", values, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2Between(String value1, String value2) {
            addCriterion("retain2 between", value1, value2, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain2NotBetween(String value1, String value2) {
            addCriterion("retain2 not between", value1, value2, "retain2");
            return (Criteria) this;
        }

        public Criteria andRetain3IsNull() {
            addCriterion("retain3 is null");
            return (Criteria) this;
        }

        public Criteria andRetain3IsNotNull() {
            addCriterion("retain3 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain3EqualTo(String value) {
            addCriterion("retain3 =", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3NotEqualTo(String value) {
            addCriterion("retain3 <>", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3GreaterThan(String value) {
            addCriterion("retain3 >", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3GreaterThanOrEqualTo(String value) {
            addCriterion("retain3 >=", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3LessThan(String value) {
            addCriterion("retain3 <", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3LessThanOrEqualTo(String value) {
            addCriterion("retain3 <=", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3Like(String value) {
            addCriterion("retain3 like", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3NotLike(String value) {
            addCriterion("retain3 not like", value, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3In(List<String> values) {
            addCriterion("retain3 in", values, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3NotIn(List<String> values) {
            addCriterion("retain3 not in", values, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3Between(String value1, String value2) {
            addCriterion("retain3 between", value1, value2, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain3NotBetween(String value1, String value2) {
            addCriterion("retain3 not between", value1, value2, "retain3");
            return (Criteria) this;
        }

        public Criteria andRetain4IsNull() {
            addCriterion("retain4 is null");
            return (Criteria) this;
        }

        public Criteria andRetain4IsNotNull() {
            addCriterion("retain4 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain4EqualTo(String value) {
            addCriterion("retain4 =", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4NotEqualTo(String value) {
            addCriterion("retain4 <>", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4GreaterThan(String value) {
            addCriterion("retain4 >", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4GreaterThanOrEqualTo(String value) {
            addCriterion("retain4 >=", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4LessThan(String value) {
            addCriterion("retain4 <", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4LessThanOrEqualTo(String value) {
            addCriterion("retain4 <=", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4Like(String value) {
            addCriterion("retain4 like", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4NotLike(String value) {
            addCriterion("retain4 not like", value, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4In(List<String> values) {
            addCriterion("retain4 in", values, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4NotIn(List<String> values) {
            addCriterion("retain4 not in", values, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4Between(String value1, String value2) {
            addCriterion("retain4 between", value1, value2, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain4NotBetween(String value1, String value2) {
            addCriterion("retain4 not between", value1, value2, "retain4");
            return (Criteria) this;
        }

        public Criteria andRetain5IsNull() {
            addCriterion("retain5 is null");
            return (Criteria) this;
        }

        public Criteria andRetain5IsNotNull() {
            addCriterion("retain5 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain5EqualTo(String value) {
            addCriterion("retain5 =", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5NotEqualTo(String value) {
            addCriterion("retain5 <>", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5GreaterThan(String value) {
            addCriterion("retain5 >", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5GreaterThanOrEqualTo(String value) {
            addCriterion("retain5 >=", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5LessThan(String value) {
            addCriterion("retain5 <", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5LessThanOrEqualTo(String value) {
            addCriterion("retain5 <=", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5Like(String value) {
            addCriterion("retain5 like", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5NotLike(String value) {
            addCriterion("retain5 not like", value, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5In(List<String> values) {
            addCriterion("retain5 in", values, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5NotIn(List<String> values) {
            addCriterion("retain5 not in", values, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5Between(String value1, String value2) {
            addCriterion("retain5 between", value1, value2, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain5NotBetween(String value1, String value2) {
            addCriterion("retain5 not between", value1, value2, "retain5");
            return (Criteria) this;
        }

        public Criteria andRetain6IsNull() {
            addCriterion("retain6 is null");
            return (Criteria) this;
        }

        public Criteria andRetain6IsNotNull() {
            addCriterion("retain6 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain6EqualTo(String value) {
            addCriterion("retain6 =", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6NotEqualTo(String value) {
            addCriterion("retain6 <>", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6GreaterThan(String value) {
            addCriterion("retain6 >", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6GreaterThanOrEqualTo(String value) {
            addCriterion("retain6 >=", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6LessThan(String value) {
            addCriterion("retain6 <", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6LessThanOrEqualTo(String value) {
            addCriterion("retain6 <=", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6Like(String value) {
            addCriterion("retain6 like", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6NotLike(String value) {
            addCriterion("retain6 not like", value, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6In(List<String> values) {
            addCriterion("retain6 in", values, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6NotIn(List<String> values) {
            addCriterion("retain6 not in", values, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6Between(String value1, String value2) {
            addCriterion("retain6 between", value1, value2, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain6NotBetween(String value1, String value2) {
            addCriterion("retain6 not between", value1, value2, "retain6");
            return (Criteria) this;
        }

        public Criteria andRetain7IsNull() {
            addCriterion("retain7 is null");
            return (Criteria) this;
        }

        public Criteria andRetain7IsNotNull() {
            addCriterion("retain7 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain7EqualTo(String value) {
            addCriterion("retain7 =", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7NotEqualTo(String value) {
            addCriterion("retain7 <>", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7GreaterThan(String value) {
            addCriterion("retain7 >", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7GreaterThanOrEqualTo(String value) {
            addCriterion("retain7 >=", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7LessThan(String value) {
            addCriterion("retain7 <", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7LessThanOrEqualTo(String value) {
            addCriterion("retain7 <=", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7Like(String value) {
            addCriterion("retain7 like", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7NotLike(String value) {
            addCriterion("retain7 not like", value, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7In(List<String> values) {
            addCriterion("retain7 in", values, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7NotIn(List<String> values) {
            addCriterion("retain7 not in", values, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7Between(String value1, String value2) {
            addCriterion("retain7 between", value1, value2, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain7NotBetween(String value1, String value2) {
            addCriterion("retain7 not between", value1, value2, "retain7");
            return (Criteria) this;
        }

        public Criteria andRetain8IsNull() {
            addCriterion("retain8 is null");
            return (Criteria) this;
        }

        public Criteria andRetain8IsNotNull() {
            addCriterion("retain8 is not null");
            return (Criteria) this;
        }

        public Criteria andRetain8EqualTo(String value) {
            addCriterion("retain8 =", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8NotEqualTo(String value) {
            addCriterion("retain8 <>", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8GreaterThan(String value) {
            addCriterion("retain8 >", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8GreaterThanOrEqualTo(String value) {
            addCriterion("retain8 >=", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8LessThan(String value) {
            addCriterion("retain8 <", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8LessThanOrEqualTo(String value) {
            addCriterion("retain8 <=", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8Like(String value) {
            addCriterion("retain8 like", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8NotLike(String value) {
            addCriterion("retain8 not like", value, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8In(List<String> values) {
            addCriterion("retain8 in", values, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8NotIn(List<String> values) {
            addCriterion("retain8 not in", values, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8Between(String value1, String value2) {
            addCriterion("retain8 between", value1, value2, "retain8");
            return (Criteria) this;
        }

        public Criteria andRetain8NotBetween(String value1, String value2) {
            addCriterion("retain8 not between", value1, value2, "retain8");
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