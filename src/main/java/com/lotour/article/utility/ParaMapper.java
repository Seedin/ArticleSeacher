package com.lotour.article.utility;

import com.lotour.article.entity.DateLimitType;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ljing on 2015/5/13.
 * 参数转换类
 */
public class ParaMapper
{
    /**
     * 时间类型限制转换为Long计数
     * @param para
     * @return
     */
    public static long DateLimitTypeToLongLimit(int para)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();
        DateLimitType limitType = DateLimitType.NONE;
        for (DateLimitType dlType: DateLimitType.values())
        {
            if (dlType.ordinal() == para)
            {
                limitType = dlType;
                break;
            }
        }
        switch (limitType)
        {
            case NONE:
                return 0;
            case TODAY:
                cal.setTime(today);
                cal.add(Calendar.DAY_OF_YEAR, -1);
                return cal.getTime().getTime();
//                return now.getTime() - 86400000;
            case INAWEEK:
                cal.setTime(today);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                return cal.getTime().getTime();
//                return now.getTime() - 604800000;
            case INAMONTH:
                cal.setTime(today);
                cal.add(Calendar.MONTH, -1);
                return cal.getTime().getTime();
            case INASEASON:
                cal.setTime(today);
                cal.add(Calendar.MONTH, -3);
                return cal.getTime().getTime();
            case INAYEAR:
                cal.setTime(today);
                cal.add(Calendar.YEAR, -1);
                return cal.getTime().getTime();
            default:
                return 0;
        }
    }
}
