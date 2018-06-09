//存放生成的java文件的包
namespace java com.shengsiyuan.netty.thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

//定义一个结构体
 struct Pride {
    1:optional int age,
    2:optional String name,
    3:optional String address
 }

 exception DaoException {
    1:optional String message,
    2:optional String callStack,
    3:optional String date
 }

 service PrideService {
    Pride getPrideByUsername(1:required String username) throws (1:DaoException daoException),
    void savePride(1:required Pride pride) throws (1:DaoException daoException)
 }
