package com.facebank.usersupport.pojo;

import java.util.ArrayList;
import java.util.List;

public class TestFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestFormExample() {
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

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(Long value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(Long value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(Long value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(Long value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(Long value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(Long value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<Long> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<Long> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(Long value1, Long value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(Long value1, Long value2) {
            addCriterion("form_id not between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormServiceIsNull() {
            addCriterion("form_service is null");
            return (Criteria) this;
        }

        public Criteria andFormServiceIsNotNull() {
            addCriterion("form_service is not null");
            return (Criteria) this;
        }

        public Criteria andFormServiceEqualTo(String value) {
            addCriterion("form_service =", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceNotEqualTo(String value) {
            addCriterion("form_service <>", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceGreaterThan(String value) {
            addCriterion("form_service >", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceGreaterThanOrEqualTo(String value) {
            addCriterion("form_service >=", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceLessThan(String value) {
            addCriterion("form_service <", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceLessThanOrEqualTo(String value) {
            addCriterion("form_service <=", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceLike(String value) {
            addCriterion("form_service like", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceNotLike(String value) {
            addCriterion("form_service not like", value, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceIn(List<String> values) {
            addCriterion("form_service in", values, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceNotIn(List<String> values) {
            addCriterion("form_service not in", values, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceBetween(String value1, String value2) {
            addCriterion("form_service between", value1, value2, "formService");
            return (Criteria) this;
        }

        public Criteria andFormServiceNotBetween(String value1, String value2) {
            addCriterion("form_service not between", value1, value2, "formService");
            return (Criteria) this;
        }

        public Criteria andFormBranchIsNull() {
            addCriterion("form_branch is null");
            return (Criteria) this;
        }

        public Criteria andFormBranchIsNotNull() {
            addCriterion("form_branch is not null");
            return (Criteria) this;
        }

        public Criteria andFormBranchEqualTo(String value) {
            addCriterion("form_branch =", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchNotEqualTo(String value) {
            addCriterion("form_branch <>", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchGreaterThan(String value) {
            addCriterion("form_branch >", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchGreaterThanOrEqualTo(String value) {
            addCriterion("form_branch >=", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchLessThan(String value) {
            addCriterion("form_branch <", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchLessThanOrEqualTo(String value) {
            addCriterion("form_branch <=", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchLike(String value) {
            addCriterion("form_branch like", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchNotLike(String value) {
            addCriterion("form_branch not like", value, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchIn(List<String> values) {
            addCriterion("form_branch in", values, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchNotIn(List<String> values) {
            addCriterion("form_branch not in", values, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchBetween(String value1, String value2) {
            addCriterion("form_branch between", value1, value2, "formBranch");
            return (Criteria) this;
        }

        public Criteria andFormBranchNotBetween(String value1, String value2) {
            addCriterion("form_branch not between", value1, value2, "formBranch");
            return (Criteria) this;
        }

        public Criteria andIsTestIsNull() {
            addCriterion("is_test is null");
            return (Criteria) this;
        }

        public Criteria andIsTestIsNotNull() {
            addCriterion("is_test is not null");
            return (Criteria) this;
        }

        public Criteria andIsTestEqualTo(Integer value) {
            addCriterion("is_test =", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotEqualTo(Integer value) {
            addCriterion("is_test <>", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestGreaterThan(Integer value) {
            addCriterion("is_test >", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_test >=", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestLessThan(Integer value) {
            addCriterion("is_test <", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestLessThanOrEqualTo(Integer value) {
            addCriterion("is_test <=", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestIn(List<Integer> values) {
            addCriterion("is_test in", values, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotIn(List<Integer> values) {
            addCriterion("is_test not in", values, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestBetween(Integer value1, Integer value2) {
            addCriterion("is_test between", value1, value2, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_test not between", value1, value2, "isTest");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeIsNull() {
            addCriterion("influence_scope is null");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeIsNotNull() {
            addCriterion("influence_scope is not null");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeEqualTo(String value) {
            addCriterion("influence_scope =", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeNotEqualTo(String value) {
            addCriterion("influence_scope <>", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeGreaterThan(String value) {
            addCriterion("influence_scope >", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeGreaterThanOrEqualTo(String value) {
            addCriterion("influence_scope >=", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeLessThan(String value) {
            addCriterion("influence_scope <", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeLessThanOrEqualTo(String value) {
            addCriterion("influence_scope <=", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeLike(String value) {
            addCriterion("influence_scope like", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeNotLike(String value) {
            addCriterion("influence_scope not like", value, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeIn(List<String> values) {
            addCriterion("influence_scope in", values, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeNotIn(List<String> values) {
            addCriterion("influence_scope not in", values, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeBetween(String value1, String value2) {
            addCriterion("influence_scope between", value1, value2, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andInfluenceScopeNotBetween(String value1, String value2) {
            addCriterion("influence_scope not between", value1, value2, "influenceScope");
            return (Criteria) this;
        }

        public Criteria andIsReviewIsNull() {
            addCriterion("is_review is null");
            return (Criteria) this;
        }

        public Criteria andIsReviewIsNotNull() {
            addCriterion("is_review is not null");
            return (Criteria) this;
        }

        public Criteria andIsReviewEqualTo(Integer value) {
            addCriterion("is_review =", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotEqualTo(Integer value) {
            addCriterion("is_review <>", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewGreaterThan(Integer value) {
            addCriterion("is_review >", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_review >=", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewLessThan(Integer value) {
            addCriterion("is_review <", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewLessThanOrEqualTo(Integer value) {
            addCriterion("is_review <=", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewIn(List<Integer> values) {
            addCriterion("is_review in", values, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotIn(List<Integer> values) {
            addCriterion("is_review not in", values, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewBetween(Integer value1, Integer value2) {
            addCriterion("is_review between", value1, value2, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotBetween(Integer value1, Integer value2) {
            addCriterion("is_review not between", value1, value2, "isReview");
            return (Criteria) this;
        }

        public Criteria andOtherChangeIsNull() {
            addCriterion("other_change is null");
            return (Criteria) this;
        }

        public Criteria andOtherChangeIsNotNull() {
            addCriterion("other_change is not null");
            return (Criteria) this;
        }

        public Criteria andOtherChangeEqualTo(String value) {
            addCriterion("other_change =", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeNotEqualTo(String value) {
            addCriterion("other_change <>", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeGreaterThan(String value) {
            addCriterion("other_change >", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeGreaterThanOrEqualTo(String value) {
            addCriterion("other_change >=", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeLessThan(String value) {
            addCriterion("other_change <", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeLessThanOrEqualTo(String value) {
            addCriterion("other_change <=", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeLike(String value) {
            addCriterion("other_change like", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeNotLike(String value) {
            addCriterion("other_change not like", value, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeIn(List<String> values) {
            addCriterion("other_change in", values, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeNotIn(List<String> values) {
            addCriterion("other_change not in", values, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeBetween(String value1, String value2) {
            addCriterion("other_change between", value1, value2, "otherChange");
            return (Criteria) this;
        }

        public Criteria andOtherChangeNotBetween(String value1, String value2) {
            addCriterion("other_change not between", value1, value2, "otherChange");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Long value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Long value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Long value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Long value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Long value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Long> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Long> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Long value1, Long value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Long value1, Long value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserIsNull() {
            addCriterion("accept_user is null");
            return (Criteria) this;
        }

        public Criteria andAcceptUserIsNotNull() {
            addCriterion("accept_user is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptUserEqualTo(Long value) {
            addCriterion("accept_user =", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserNotEqualTo(Long value) {
            addCriterion("accept_user <>", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserGreaterThan(Long value) {
            addCriterion("accept_user >", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserGreaterThanOrEqualTo(Long value) {
            addCriterion("accept_user >=", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserLessThan(Long value) {
            addCriterion("accept_user <", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserLessThanOrEqualTo(Long value) {
            addCriterion("accept_user <=", value, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserIn(List<Long> values) {
            addCriterion("accept_user in", values, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserNotIn(List<Long> values) {
            addCriterion("accept_user not in", values, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserBetween(Long value1, Long value2) {
            addCriterion("accept_user between", value1, value2, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andAcceptUserNotBetween(Long value1, Long value2) {
            addCriterion("accept_user not between", value1, value2, "acceptUser");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Long value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Long> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Long> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Long value1, Long value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIsNull() {
            addCriterion("form_status is null");
            return (Criteria) this;
        }

        public Criteria andFormStatusIsNotNull() {
            addCriterion("form_status is not null");
            return (Criteria) this;
        }

        public Criteria andFormStatusEqualTo(Integer value) {
            addCriterion("form_status =", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusNotEqualTo(Integer value) {
            addCriterion("form_status <>", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusGreaterThan(Integer value) {
            addCriterion("form_status >", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("form_status >=", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusLessThan(Integer value) {
            addCriterion("form_status <", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusLessThanOrEqualTo(Integer value) {
            addCriterion("form_status <=", value, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusIn(List<Integer> values) {
            addCriterion("form_status in", values, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusNotIn(List<Integer> values) {
            addCriterion("form_status not in", values, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusBetween(Integer value1, Integer value2) {
            addCriterion("form_status between", value1, value2, "formStatus");
            return (Criteria) this;
        }

        public Criteria andFormStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("form_status not between", value1, value2, "formStatus");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Long value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Long value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Long value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Long value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Long value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Long> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Long> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Long value1, Long value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Long value1, Long value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Long value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Long value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Long value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Long value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Long value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Long> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Long> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Long value1, Long value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Long value1, Long value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
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