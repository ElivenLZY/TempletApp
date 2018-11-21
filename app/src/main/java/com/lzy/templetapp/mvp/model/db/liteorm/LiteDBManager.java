package com.lzy.templetapp.mvp.model.db.liteorm;

import android.database.sqlite.SQLiteDatabase;

import com.common.utils.CollectionUtils;
import com.common.utils.UIUtils;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.SQLiteHelper;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.Collection;
import java.util.List;

/**
 * liteOrm数据库管理对象
 * 介于greenDao和Realm之间的数据库orm框架（小巧，快速，简单易用）
 * <p>
 * 一个数据库对应一个LiteOrm的实例，如果一个App只有一个数据库，那么LiteOrm应该是全局单例的。
 * 如果多次新建LiteOrm实例，系统会提示您应该关闭之前的数据库，也可能会引起其他未知错误。
 * <p>
 * 创建数据库细节 ：
 * 细节一：每次启动都会调用这个方法，是否会重复创建，或者说怎么判断这个数据库已经存在。
 * 回答：尽在第一次创建，开发这不用关心存在否。
 * <p>
 * 细节二：如果指定目录创建在SD卡上，卸载APP之后，重新安装，以前的创建的数据库还存在
 * 回答：如果不想这样，就不要指定创建在SD卡上面，默认创建就在APP里。卸载后数据库就不存在了
 * DB_NAME = Environment.getExternalStorageDirectory().getAbsolutePath()+"/pinme.db";
 * <p>
 * 细节三：创建数据库有两种模式，第一种是单一操作没有级联关系的，第二种是级联操作。
 * 创建单一操作模式：LiteOrm.newSingleInstance(_activity, DB_NAME);
 * 创建级联操作模式：LiteOrm.newCascadeInstance(_activity, DB_NAME);
 */
public class LiteDBManager implements SQLiteHelper.OnUpdateListener {

    private volatile static LiteDBManager instance = null;

    private String DBName;//一个用户一个数据库
    private final int DBVersion = 1;

    private LiteOrm liteOrm = null;

    public LiteDBManager() {
        long userId = -1;
        DBName = "cnst_" + userId + ".db";//一个用户一个数据库
        if (liteOrm == null) {
            DataBaseConfig config = new DataBaseConfig(UIUtils.getAppContext(), DBName);
            config.debugged = true; // open the log
            config.dbVersion = DBVersion; // set database version
            config.onUpdateListener = this; // set database update listener
            //独立操作
            liteOrm = LiteOrm.newSingleInstance(config);
            //级联操作
//            liteOrm = LiteOrm.newCascadeInstance(config);

        }
    }

    //双重检查锁定（DCL）方式
    public static LiteDBManager getInstance() {
        if (instance == null) {
            synchronized (LiteDBManager.class) {
                if (instance == null) {
                    instance = new LiteDBManager();
                }
            }

        }
        return instance;
    }


    @Override
    public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * 插入一条记录（存在也不更新）
     *
     * @param t
     */
    public <T> boolean insert(T t) {
        if (t == null) return false;
        return liteOrm.insert(t) > 0 ? true : false;
    }

    /**
     * 插入所有记录（存在也不更新）
     *
     * @param list
     */
    public <T> boolean insertAll(Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) return false;
        return liteOrm.insert(list) > 0 ? true : false;
    }

    /**
     * 保存一条数据（插入or更新）
     *
     * @param t
     */
    public <T> boolean save(T t) {
        if (t == null) return false;
        return liteOrm.save(t) > 0 ? true : false;
    }

    /**
     * 保存所有数据（插入or更新）
     *
     * @param list
     */
    public <T> boolean savaAll(Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) return false;
        return liteOrm.save(list) > 0 ? true : false;
    }

    /**
     * 查询第一条数据
     *
     * @param cla
     * @return
     */
    public <T> T getQueryFirst(Class<T> cla) {
        if (cla == null) return null;
        List<T> list = getQueryAll(cla);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    /**
     * 查询所有
     *
     * @param cla
     * @return
     */
    public <T> List<T> getQueryAll(Class<T> cla) {
        if (cla == null) return null;
        return liteOrm.query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     *
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, String value) {
        if (cla == null) return null;
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", new String[]{value}));
    }

    /**
     * 查询  某字段 等于 Value的值的第一条数据
     *
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> T getQueryByWhereFirst(Class<T> cla, String field, String value) {
        List<T> list = getQueryByWhere(cla, field, value);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    /**
     * 查询
     *
     * @return
     */
    public <T> List<T> getQueryByBuild(QueryBuilder queryBuilder) {
        if (queryBuilder == null) return null;
        return liteOrm.<T>query(queryBuilder);
    }

    /**
     * 查询数据第一条
     *
     * @return
     */
    public <T> T getQueryByBuildFirst(QueryBuilder queryBuilder) {
        List<T> list = getQueryByBuild(queryBuilder);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    /**
     * 查询  某字段 等于 Value的值  可以指定从1-20，就是分页
     *
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, String value, int start, int length) {
        if (cla == null) return null;
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", new String[]{value}).limit(start, length));
    }


    /**
     * 删除
     */
    public <T> boolean delete(Object obj) {
        if (obj == null) return false;
        int line = liteOrm.delete(obj);
        return line > 0 ? true : false;
    }

    /**
     * 删除
     */
    public <T> boolean deleteList(Collection<T> var1) {
        if (var1 == null) return false;
        int line = liteOrm.delete(var1);
        return line > 0 ? true : false;
    }

    /**
     * 删除
     */
    public <T> boolean deleteByWhere(WhereBuilder whereBuilder) {
        if (whereBuilder == null) return false;
        int line = liteOrm.delete(whereBuilder);
        return line > 0 ? true : false;
    }

    /**
     * 删除所有 某字段等于 Vlaue的值
     *
     * @param cla
     * @param field
     * @param value
     */
    public <T> boolean deleteByWhere(Class<T> cla, String field, String value) {
        if (cla == null) return false;
        int line = liteOrm.delete(WhereBuilder.create(cla).where(field + "=?", new String[]{value}));
        return line > 0 ? true : false;
    }

    /**
     * 删除所有
     *
     * @param cla
     */
    public <T> boolean deleteAll(Class<T> cla) {
        if (cla == null) return false;
        return liteOrm.deleteAll(cla) > 0 ? true : false;
    }

    /**
     * 仅在以存在时更新
     *
     * @param t
     */
    public <T> boolean update(T t) {
        if (t == null) return false;
        return liteOrm.update(t) > 0 ? true : false;

    }

    public <T> boolean updateALL(Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) return false;
        return liteOrm.update(list) > 0 ? true : false;
    }

    public <T> boolean update(WhereBuilder var1, ColumnsValue var2) {
        if (var1 == null) return false;
        return liteOrm.update(var1, var2, ConflictAlgorithm.Fail) > 0 ? true : false;

    }

    /**
     * 获取liteOrm数据库操作对象
     */
    public LiteOrm getLiteDB() {
        return liteOrm;
    }

    /**
     * 删除数据库
     */
    public boolean deleteLiteDB() {
        return liteOrm.deleteDatabase();
    }

    /**
     * 关闭销毁数据库,一般用作多账户对应多数据库时重新初始化数据库参数
     **/
    public void releaseDataBase() {
        liteOrm.close();
        liteOrm = null;
        instance = null;

    }


//http://www.jianshu.com/p/0d72226ef434
//    http://blog.csdn.net/napoleonbai/article/details/41958725

//    - 保存（插入or更新）
//   School school = new School("hello");
//   liteOrm.save(school);
//   - 插入
//    Book book = new Book("good");
//  liteOrm.insert(book, ConflictAlgorithm.Abort);
//  - 更新
//  book.setIndex(1988);
//  book.setAuthor("hehe");
//  liteOrm.update(book);
//  - 更新指定列
//    // 把所有书的author强制批量改为liter
//    HashMap<String, Object> bookIdMap = new HashMap<String, Object>();
//  bookIdMap.put(Book.COL_AUTHOR, "liter");
//  liteOrm.update(bookList, new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);
//  // 仅 author 这一列更新为该对象的最新值。
//  //liteOrm.update(bookList, new ColumnsValue(new String[]{Book.COL_AUTHOR}, null), ConflictAlgorithm.Fail);
//  - 查询
//    List list = liteOrm.query(Book.class);
//  OrmLog.i(TAG, list);
//  - 查找 使用QueryBuilder
//    List<Student> list = liteOrm.query(new QueryBuilder<Student>(Student.class)
//            .where(Person.COL_NAME + " LIKE ?", new String[]{"%0"})
//            .whereAppendAnd()
//            .whereAppend(Person.COL_NAME + " LIKE ?", new String[]{"%s%"}));
//  OrmLog.i(TAG, list);
//  - 查询 根据ID
//    Student student = liteOrm.queryById(student1.getId(), Student.class);
//  OrmLog.i(TAG, student);
//  - 查询 任意
//    List<Book> books = liteOrm.query(new QueryBuilder<Book>(Book.class)
//            .columns(new String[]{"id", "author", Book.COL_INDEX})
//            .distinct(true)
//            .whereGreaterThan("id", 0)
//            .whereAppendAnd()
//            .whereLessThan("id", 10000)
//            .limit(6, 9)
//            .appendOrderAscBy(Book.COL_INDEX));
//  OrmLog.i(TAG, books);
//  - 删除 实体
//  // 删除 student-0
//  liteOrm.delete(student0);
//  - 删除 指定数量
//  // 按id升序，删除[2, size-1]，结果：仅保留第一个和最后一个
//  // 最后一个参数可为null，默认按 id 升序排列
//  liteOrm.delete(Book.class, 2, bookList.size() - 1, "id");
//  - 删除 使用WhereBuilder
//  // 删除 student-1
//  liteOrm.delete(new WhereBuilder(Student.class)
//        .where(Person.COL_NAME + " LIKE ?", new String[]{"%1%"})
//            .and()
//        .greaterThan("id", 0)
//        .and()
//        .lessThan("id", 10000));
//  - 删除全部
//  // 连同其关联的classes，classes关联的其他对象一带删除
//  liteOrm.deleteAll(School.class);
//  liteOrm.deleteAll(Book.class);
//
//
//  // 顺带测试：连库文件一起删掉
//  liteOrm.deleteDatabase();
//  // 顺带测试：然后重建一个新库
//  liteOrm.openOrCreateDatabase();
//  // 满血复活


}
