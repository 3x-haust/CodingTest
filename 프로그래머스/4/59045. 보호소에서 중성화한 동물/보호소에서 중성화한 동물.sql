SELECT B.ANIMAL_ID, B.ANIMAL_TYPE, B.NAME
FROM ANIMAL_INS A
JOIN ANIMAL_OUTS B ON A.ANIMAL_ID = B.ANIMAL_ID
WHERE A.SEX_UPON_INTAKE LIKE "%Intact%" AND (B.SEX_UPON_OUTCOME LIKE "%Spayed%" OR B.SEX_UPON_OUTCOME LIKE "%Neutered%");

# SELECT B.USER_ID, B.NICKNAME, SUM(A.PRICE) AS TOTAL_SALES FROM USED_GOODS_BOARD A
# JOIN USED_GOODS_USER B ON A.WRITER_ID = B.USER_ID
# WHERE A.STATUS = 'DONE'
# GROUP BY B.USER_ID
# HAVING TOTAL_SALES >= 700000
# ORDER BY TOTAL_SALES