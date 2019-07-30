package com.payProject.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("userId like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("userId not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("userName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("userName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("userName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("userName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("userName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("userName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("userName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("userName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNull() {
            addCriterion("userPassword is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("userPassword is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("userPassword =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("userPassword <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("userPassword >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("userPassword >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("userPassword <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("userPassword <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("userPassword like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("userPassword not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("userPassword in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("userPassword not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("userPassword between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("userPassword not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserSaltIsNull() {
            addCriterion("userSalt is null");
            return (Criteria) this;
        }

        public Criteria andUserSaltIsNotNull() {
            addCriterion("userSalt is not null");
            return (Criteria) this;
        }

        public Criteria andUserSaltEqualTo(String value) {
            addCriterion("userSalt =", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltNotEqualTo(String value) {
            addCriterion("userSalt <>", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltGreaterThan(String value) {
            addCriterion("userSalt >", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltGreaterThanOrEqualTo(String value) {
            addCriterion("userSalt >=", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltLessThan(String value) {
            addCriterion("userSalt <", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltLessThanOrEqualTo(String value) {
            addCriterion("userSalt <=", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltLike(String value) {
            addCriterion("userSalt like", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltNotLike(String value) {
            addCriterion("userSalt not like", value, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltIn(List<String> values) {
            addCriterion("userSalt in", values, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltNotIn(List<String> values) {
            addCriterion("userSalt not in", values, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltBetween(String value1, String value2) {
            addCriterion("userSalt between", value1, value2, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserSaltNotBetween(String value1, String value2) {
            addCriterion("userSalt not between", value1, value2, "userSalt");
            return (Criteria) this;
        }

        public Criteria andUserMailIsNull() {
            addCriterion("userMail is null");
            return (Criteria) this;
        }

        public Criteria andUserMailIsNotNull() {
            addCriterion("userMail is not null");
            return (Criteria) this;
        }

        public Criteria andUserMailEqualTo(String value) {
            addCriterion("userMail =", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotEqualTo(String value) {
            addCriterion("userMail <>", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailGreaterThan(String value) {
            addCriterion("userMail >", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailGreaterThanOrEqualTo(String value) {
            addCriterion("userMail >=", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailLessThan(String value) {
            addCriterion("userMail <", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailLessThanOrEqualTo(String value) {
            addCriterion("userMail <=", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailLike(String value) {
            addCriterion("userMail like", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotLike(String value) {
            addCriterion("userMail not like", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailIn(List<String> values) {
            addCriterion("userMail in", values, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotIn(List<String> values) {
            addCriterion("userMail not in", values, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailBetween(String value1, String value2) {
            addCriterion("userMail between", value1, value2, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotBetween(String value1, String value2) {
            addCriterion("userMail not between", value1, value2, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("userPhone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("userPhone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("userPhone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("userPhone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("userPhone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("userPhone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("userPhone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("userPhone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("userPhone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("userPhone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("userPhone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("userPhone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("userPhone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("userPhone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserQQIsNull() {
            addCriterion("userQQ is null");
            return (Criteria) this;
        }

        public Criteria andUserQQIsNotNull() {
            addCriterion("userQQ is not null");
            return (Criteria) this;
        }

        public Criteria andUserQQEqualTo(String value) {
            addCriterion("userQQ =", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQNotEqualTo(String value) {
            addCriterion("userQQ <>", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQGreaterThan(String value) {
            addCriterion("userQQ >", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQGreaterThanOrEqualTo(String value) {
            addCriterion("userQQ >=", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQLessThan(String value) {
            addCriterion("userQQ <", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQLessThanOrEqualTo(String value) {
            addCriterion("userQQ <=", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQLike(String value) {
            addCriterion("userQQ like", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQNotLike(String value) {
            addCriterion("userQQ not like", value, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQIn(List<String> values) {
            addCriterion("userQQ in", values, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQNotIn(List<String> values) {
            addCriterion("userQQ not in", values, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQBetween(String value1, String value2) {
            addCriterion("userQQ between", value1, value2, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserQQNotBetween(String value1, String value2) {
            addCriterion("userQQ not between", value1, value2, "userQQ");
            return (Criteria) this;
        }

        public Criteria andUserWecharIsNull() {
            addCriterion("userWechar is null");
            return (Criteria) this;
        }

        public Criteria andUserWecharIsNotNull() {
            addCriterion("userWechar is not null");
            return (Criteria) this;
        }

        public Criteria andUserWecharEqualTo(String value) {
            addCriterion("userWechar =", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharNotEqualTo(String value) {
            addCriterion("userWechar <>", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharGreaterThan(String value) {
            addCriterion("userWechar >", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharGreaterThanOrEqualTo(String value) {
            addCriterion("userWechar >=", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharLessThan(String value) {
            addCriterion("userWechar <", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharLessThanOrEqualTo(String value) {
            addCriterion("userWechar <=", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharLike(String value) {
            addCriterion("userWechar like", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharNotLike(String value) {
            addCriterion("userWechar not like", value, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharIn(List<String> values) {
            addCriterion("userWechar in", values, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharNotIn(List<String> values) {
            addCriterion("userWechar not in", values, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharBetween(String value1, String value2) {
            addCriterion("userWechar between", value1, value2, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserWecharNotBetween(String value1, String value2) {
            addCriterion("userWechar not between", value1, value2, "userWechar");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("userType is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("userType is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("userType =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("userType <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("userType >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("userType >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("userType <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("userType <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("userType in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("userType not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("userType between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("userType not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNull() {
            addCriterion("userAddress is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNotNull() {
            addCriterion("userAddress is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressEqualTo(String value) {
            addCriterion("userAddress =", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotEqualTo(String value) {
            addCriterion("userAddress <>", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThan(String value) {
            addCriterion("userAddress >", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("userAddress >=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThan(String value) {
            addCriterion("userAddress <", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThanOrEqualTo(String value) {
            addCriterion("userAddress <=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLike(String value) {
            addCriterion("userAddress like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotLike(String value) {
            addCriterion("userAddress not like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIn(List<String> values) {
            addCriterion("userAddress in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotIn(List<String> values) {
            addCriterion("userAddress not in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressBetween(String value1, String value2) {
            addCriterion("userAddress between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotBetween(String value1, String value2) {
            addCriterion("userAddress not between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserCityIsNull() {
            addCriterion("userCity is null");
            return (Criteria) this;
        }

        public Criteria andUserCityIsNotNull() {
            addCriterion("userCity is not null");
            return (Criteria) this;
        }

        public Criteria andUserCityEqualTo(String value) {
            addCriterion("userCity =", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityNotEqualTo(String value) {
            addCriterion("userCity <>", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityGreaterThan(String value) {
            addCriterion("userCity >", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityGreaterThanOrEqualTo(String value) {
            addCriterion("userCity >=", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityLessThan(String value) {
            addCriterion("userCity <", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityLessThanOrEqualTo(String value) {
            addCriterion("userCity <=", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityLike(String value) {
            addCriterion("userCity like", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityNotLike(String value) {
            addCriterion("userCity not like", value, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityIn(List<String> values) {
            addCriterion("userCity in", values, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityNotIn(List<String> values) {
            addCriterion("userCity not in", values, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityBetween(String value1, String value2) {
            addCriterion("userCity between", value1, value2, "userCity");
            return (Criteria) this;
        }

        public Criteria andUserCityNotBetween(String value1, String value2) {
            addCriterion("userCity not between", value1, value2, "userCity");
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
            addCriterionForJDBCDate("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime not between", value1, value2, "createTime");
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
            addCriterionForJDBCDate("submitTime =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("submitTime >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterionForJDBCDate("submitTime <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitTime <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterionForJDBCDate("submitTime in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("submitTime not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitTime between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitTime not between", value1, value2, "submitTime");
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