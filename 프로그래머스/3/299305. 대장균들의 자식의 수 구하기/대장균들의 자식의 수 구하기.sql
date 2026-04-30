SELECT ED.ID, count(EDD.id) as CHILD_COUNT
FROM ECOLI_DATA ED
LEFT JOIN ECOLI_DATA EDD
ON ED.ID = EDD.PARENT_ID
group by ED.ID
ORDER BY ED.ID;

# count(*)
# 이 방식은 GROUP BY에 의해 생성된 그룹 내의 모든 행을 세는 것입니다.
# 따라서, c.ID가 NULL이어도 해당 행은 포함됩니다.
# 결과적으로, p.ID에 자식 레코드가 없더라도 부모 p 레코드와 매핑된 NULL 값을 포함하여 최소 1개의 행이 포함됩니다.