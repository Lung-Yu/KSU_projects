
enum century{
     Tenth=10,
     Eleventh,
     Twelfth,
     Thirteenth,
     Fourteenth,
     Fifteenth,
     Sixteenth,
     Seventeenth,
     Eighteenth,
     Nineteen, 
     Twentieth,
     Twenty_first,//21
     Twenty_second//22
     };
typedef struct student
       {
        char name[100];
        char Nationality[30];
        char school[50];
        char status[500];
        enum century Century;
        int score_start_year;
        double score_start_MD;
        int score_end_year;              
        double socre_end_MD;
       }STU;
#define filename "psychologydata.txt"
/*↓psychology data ↓*//* 
     century      Name         生於年       卒於年      國籍      學派          身分 
    |Nineteen | |馮特      | |1832,08.26| |1920,08.31| |德國   | |結構主義    | |教育學家                                               |
    |Nineteen | |詹姆斯    | |1842,01.11| |1910,08.26| |美國   | |機能主義    | |教育學家                                               |
    |Nineteen | |華生      | |1878,01.09| |1958,09.25| |美國   | |行為主義    | |行為主義心理學創始人                                   |
    |Nineteen | |托爾曼    | |1886,00.00| |1959,00.00| |美國   | |新行為主義  | |以研究行為心理學著稱                                   |
    |Nineteen | |佛洛伊德  | |1856,05.06| |1939,09.23| |奧地利 | |精神分析    | |精神分析學派的創始人                                   |
    |Nineteen | |榮格      | |1875,07.26| |1961,06.06| |瑞士   | |新精神分析  | |精神分析醫師&分析心理學創始者                          |
    |Twentieth| |馬斯洛    | |1908,04.01| |1970,06.08| |美國   | |人本主義    | |提出融合精神分析心理學&行為主義心理學的人本主義心理學  |
    |Twentieth| |羅傑斯    | |1902,01.08| |1987,02.04| |美國   | |人本主義    | |人本主義心理學的創始人之一非指導室諮詢的創始人         |
    |Nineteen | |皮亞傑    | |1896,08.09| |1980,09.06| |瑞士   | |皮亞傑      | |兒童心理學家                                           |
    |Nineteen | |威特海默  | |1880,04.15| |1980,10.21| |德國   | |格式塔      | |1912建立格式塔(完形學派)                               |

*/ 
