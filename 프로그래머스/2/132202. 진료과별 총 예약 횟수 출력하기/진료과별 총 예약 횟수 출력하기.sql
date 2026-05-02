-- 코드를 입력하세요
# SELECT MCDP_CD as '진료과 코드', COUNT(PT_NO) as '5월 예약 건수'
# FROM APPOINTMENT 
# WHERE YEAR(APNT_YMD) = '2022' AND MONTH(APNT_YMD) = '5'
# group by MCDP_CD
# order by COUNT(PT_NO) asc,MCDP_CD asc;


SELECT MCDP_CD as '진료과 코드', count(*) as '5월예약건수'
FROM APPOINTMENT
WHERE YEAR(APNT_YMD) = '2022' and MONTH(APNT_YMD)='5'
group by MCDP_CD
order by count(*), MCDP_CD