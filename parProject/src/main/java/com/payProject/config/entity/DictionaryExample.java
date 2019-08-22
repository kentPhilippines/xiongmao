package com.payProject.config.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryExample() {
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

        public Criteria andDataKeyIsNull() {
            addCriterion("dataKey is null");
            return (Criteria) this;
        }

        public Criteria andDataKeyIsNotNull() {
            addCriterion("dataKey is not null");
            return (Criteria) this;
        }

        public Criteria andDataKeyEqualTo(String value) {
            addCriterion("dataKey =", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyNotEqualTo(String value) {
            addCriterion("dataKey <>", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyGreaterThan(String value) {
            addCriterion("dataKey >", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyGreaterThanOrEqualTo(String value) {
            addCriterion("dataKey >=", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyLessThan(String value) {
            addCriterion("dataKey <", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyLessThanOrEqualTo(String value) {
            addCriterion("dataKey <=", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyLike(String value) {
            addCriterion("dataKey like", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyNotLike(String value) {
            addCriterion("dataKey not like", value, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyIn(List<String> values) {
            addCriterion("dataKey in", values, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyNotIn(List<String> values) {
            addCriterion("dataKey not in", values, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyBetween(String value1, String value2) {
            addCriterion("dataKey between", value1, value2, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataKeyNotBetween(String value1, String value2) {
            addCriterion("dataKey not between", value1, value2, "dataKey");
            return (Criteria) this;
        }

        public Criteria andDataValueIsNull() {
            addCriterion("dataValue is null");
            return (Criteria) this;
        }

        public Criteria andDataValueIsNotNull() {
            addCriterion("dataValue is not null");
            return (Criteria) this;
        }

        public Criteria andDataValueEqualTo(String value) {
            addCriterion("dataValue =", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotEqualTo(String value) {
            addCriterion("dataValue <>", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueGreaterThan(String value) {
            addCriterion("dataValue >", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueGreaterThanOrEqualTo(String value) {
            addCriterion("dataValue >=", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueLessThan(String value) {
            addCriterion("dataValue <", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueLessThanOrEqualTo(String value) {
            addCriterion("dataValue <=", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueLike(String value) {
            addCriterion("dataValue like", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotLike(String value) {
            addCriterion("dataValue not like", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueIn(List<String> values) {
            addCriterion("dataValue in", values, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotIn(List<String> values) {
            addCriterion("dataValue not in", values, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueBetween(String value1, String value2) {
            addCriterion("dataValue between", value1, value2, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotBetween(String value1, String value2) {
            addCriterion("dataValue not between", value1, value2, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("dataType is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("dataType is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("dataType =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("dataType <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("dataType >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dataType >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("dataType <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("dataType <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("dataType like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("dataType not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("dataType in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("dataType not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("dataType between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("dataType not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDateSourceIsNull() {
            addCriterion("dateSource is null");
            return (Criteria) this;
        }

        public Criteria andDateSourceIsNotNull() {
            addCriterion("dateSource is not null");
            return (Criteria) this;
        }

        public Criteria andDateSourceEqualTo(String value) {
            addCriterion("dateSource =", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceNotEqualTo(String value) {
            addCriterion("dateSource <>", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceGreaterThan(String value) {
            addCriterion("dateSource >", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceGreaterThanOrEqualTo(String value) {
            addCriterion("dateSource >=", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceLessThan(String value) {
            addCriterion("dateSource <", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceLessThanOrEqualTo(String value) {
            addCriterion("dateSource <=", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceLike(String value) {
            addCriterion("dateSource like", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceNotLike(String value) {
            addCriterion("dateSource not like", value, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceIn(List<String> values) {
            addCriterion("dateSource in", values, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceNotIn(List<String> values) {
            addCriterion("dateSource not in", values, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceBetween(String value1, String value2) {
            addCriterion("dateSource between", value1, value2, "dateSource");
            return (Criteria) this;
        }

        public Criteria andDateSourceNotBetween(String value1, String value2) {
            addCriterion("dateSource not between", value1, value2, "dateSource");
            return (Criteria) this;
        }

        public Criteria andUseIsNull() {
            addCriterion("use is null");
            return (Criteria) this;
        }

        public Criteria andUseIsNotNull() {
            addCriterion("use is not null");
            return (Criteria) this;
        }

        public Criteria andUseEqualTo(String value) {
            addCriterion("use =", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotEqualTo(String value) {
            addCriterion("use <>", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseGreaterThan(String value) {
            addCriterion("use >", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseGreaterThanOrEqualTo(String value) {
            addCriterion("use >=", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseLessThan(String value) {
            addCriterion("use <", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseLessThanOrEqualTo(String value) {
            addCriterion("use <=", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseLike(String value) {
            addCriterion("use like", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotLike(String value) {
            addCriterion("use not like", value, "use");
            return (Criteria) this;
        }

        public Criteria andUseIn(List<String> values) {
            addCriterion("use in", values, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotIn(List<String> values) {
            addCriterion("use not in", values, "use");
            return (Criteria) this;
        }

        public Criteria andUseBetween(String value1, String value2) {
            addCriterion("use between", value1, value2, "use");
            return (Criteria) this;
        }

        public Criteria andUseNotBetween(String value1, String value2) {
            addCriterion("use not between", value1, value2, "use");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submitTime is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submitTime is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submitTime =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submitTime <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submitTime >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submitTime >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submitTime <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submitTime <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submitTime in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submitTime not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submitTime between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submitTime not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemIsNull() {
            addCriterion("submitSystem is null");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemIsNotNull() {
            addCriterion("submitSystem is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemEqualTo(String value) {
            addCriterion("submitSystem =", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemNotEqualTo(String value) {
            addCriterion("submitSystem <>", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemGreaterThan(String value) {
            addCriterion("submitSystem >", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemGreaterThanOrEqualTo(String value) {
            addCriterion("submitSystem >=", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemLessThan(String value) {
            addCriterion("submitSystem <", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemLessThanOrEqualTo(String value) {
            addCriterion("submitSystem <=", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemLike(String value) {
            addCriterion("submitSystem like", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemNotLike(String value) {
            addCriterion("submitSystem not like", value, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemIn(List<String> values) {
            addCriterion("submitSystem in", values, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemNotIn(List<String> values) {
            addCriterion("submitSystem not in", values, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemBetween(String value1, String value2) {
            addCriterion("submitSystem between", value1, value2, "submitSystem");
            return (Criteria) this;
        }

        public Criteria andSubmitSystemNotBetween(String value1, String value2) {
            addCriterion("submitSystem not between", value1, value2, "submitSystem");
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