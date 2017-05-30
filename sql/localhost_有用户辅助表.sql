-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 08 月 12 日 10:41
-- 服务器版本: 5.5.8
-- PHP 版本: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `seawar2`
--
CREATE DATABASE `seawar2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `seawar2`;

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库主键标识',
  `name` varchar(16) NOT NULL COMMENT '名字',
  `lgID` varchar(16) NOT NULL COMMENT '登录密码',
  `lgPwd` varchar(16) NOT NULL COMMENT '登录密码',
  `post` int(11) NOT NULL COMMENT '职位',
  `powers` varchar(32) NOT NULL COMMENT '权限(运行的操作)',
  `lastLogin` datetime NOT NULL COMMENT '最后登录时间',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `admin`
--


-- --------------------------------------------------------

--
-- 表的结构 `attack_defense_info`
--

CREATE TABLE IF NOT EXISTS `attack_defense_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attmcid` varchar(32) NOT NULL COMMENT '战报clientId',
  `builds` text NOT NULL COMMENT '防御方建筑信息',
  `obsts` text NOT NULL COMMENT '障碍',
  `amrys` text NOT NULL COMMENT '防御方兵信息',
  `teches` text NOT NULL,
  `heros` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `attack_defense_info`
--


-- --------------------------------------------------------

--
-- 表的结构 `attack_mail`
--

CREATE TABLE IF NOT EXISTS `attack_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attcid` varchar(32) NOT NULL COMMENT '战报clientId',
  `attPcid` int(11) NOT NULL COMMENT '攻击者clientId',
  `attPname` varchar(32) NOT NULL COMMENT '攻击者名称',
  `beAttPcid` int(11) NOT NULL COMMENT '被攻击者clientId',
  `beAttPname` varchar(32) NOT NULL COMMENT '被攻击者名称',
  `begin_time` bigint(20) NOT NULL COMMENT '战斗开始时间',
  `end_time` bigint(20) NOT NULL COMMENT '战斗结束时间',
  `star` int(11) NOT NULL COMMENT '攻击所打星数',
  `preGold` int(11) NOT NULL COMMENT '攻击者攻击前的金币',
  `preOil` int(11) NOT NULL COMMENT '攻击者被攻击前的油',
  `preAttRenown` int(11) NOT NULL COMMENT '攻击前攻击者的声望',
  `attRenown` int(11) NOT NULL COMMENT '攻击获得或失去的声望值',
  `attGold` int(11) NOT NULL COMMENT '攻击获得金币',
  `attOil` int(11) NOT NULL COMMENT '攻击获得的油',
  `isHitBack` bit(1) NOT NULL COMMENT '被攻击者是否反击',
  `clancid` varchar(16) NOT NULL COMMENT '攻击者当时的联盟标识',
  `cname` varchar(32) NOT NULL COMMENT '攻击者联盟名称',
  `cicon` int(11) NOT NULL COMMENT '攻击者联盟图标',
  `offenHP` int(11) NOT NULL COMMENT '攻击方所加点数HP',
  `offenAtt` int(11) NOT NULL COMMENT '攻击方所加攻击力点数',
  `defccid` varchar(16) NOT NULL COMMENT '防御方联盟标识',
  `defcname` varchar(32) NOT NULL COMMENT '防御方联盟名称',
  `defcicon` int(11) NOT NULL COMMENT '防御方联盟图标',
  `defenseHP` int(11) NOT NULL,
  `defenseAtt` int(11) NOT NULL COMMENT '防御方所加攻击力数',
  `attInfos` text NOT NULL COMMENT '攻击信息(放的兵位置，时间等等)',
  `beRewon` int(11) NOT NULL COMMENT '被攻击者获得失去声望',
  `egid` int(11) NOT NULL COMMENT '能源片gid',
  `num` int(11) NOT NULL COMMENT '能源片数量',
  `attHeros` text NOT NULL COMMENT '攻击者英雄信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `attack_mail`
--


-- --------------------------------------------------------

--
-- 表的结构 `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` int(11) NOT NULL COMMENT '聊天clientId',
  `type` int(11) NOT NULL COMMENT '聊天类型',
  `content` text NOT NULL COMMENT '内容',
  `create_time` bigint(20) NOT NULL,
  `fromId` int(11) NOT NULL,
  `fromName` varchar(32) NOT NULL,
  `toId` int(11) NOT NULL,
  `toName` varchar(32) NOT NULL,
  `clancid` varchar(16) NOT NULL COMMENT '联盟聊天用的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `chat`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan`
--

CREATE TABLE IF NOT EXISTS `clan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '工会clientId',
  `clan_name` varchar(32) NOT NULL,
  `icon` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `desc` text NOT NULL,
  `maxNum` int(11) NOT NULL COMMENT '最大人数',
  `curNum` int(11) NOT NULL COMMENT '当前数量',
  `create_time` bigint(20) NOT NULL,
  `pointHP` int(11) NOT NULL COMMENT '加生命点数(1点代表xx点血)',
  `pointAtt` int(11) NOT NULL COMMENT '攻击力点数(1点等于xx点)',
  `renownAll` int(11) NOT NULL COMMENT '总声望',
  `renownWeek` int(11) NOT NULL COMMENT '周排行',
  `nextHPGold` bigint(20) NOT NULL,
  `nextAttOil` bigint(20) NOT NULL,
  `curDonateGold` bigint(20) NOT NULL COMMENT '当前点数所捐金',
  `curDonateOil` bigint(20) NOT NULL COMMENT '当前点数所捐油',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ccid` (`ccid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan_member`
--

CREATE TABLE IF NOT EXISTS `clan_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `ucid` int(11) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '玩家唯一标识',
  `pname` varchar(32) NOT NULL COMMENT '玩家名称',
  `post` int(11) NOT NULL COMMENT '职位',
  `donateGold` int(11) NOT NULL COMMENT '捐献金币总数',
  `donateOil` int(11) NOT NULL COMMENT '捐献油总数',
  `curDGold` int(11) NOT NULL COMMENT '当前点数所捐金币',
  `curDOil` int(11) NOT NULL COMMENT '当前点数所捐的油',
  `lastDGold` bigint(20) NOT NULL COMMENT '最后一次捐献金币时间',
  `lastDOil` bigint(20) NOT NULL COMMENT '最后一次捐赠油时间',
  `renownAll` int(11) NOT NULL COMMENT '个人总荣耀',
  `renownWeek` int(11) NOT NULL COMMENT '周排行榜',
  `daynumgold` int(11) NOT NULL COMMENT '当天捐金次数',
  `daynumoil` int(11) NOT NULL COMMENT '当天捐油次数',
  `maxdaynum` int(11) NOT NULL COMMENT '当天可捐的最大次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan_member`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan_request`
--

CREATE TABLE IF NOT EXISTS `clan_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `pcid` int(11) NOT NULL COMMENT '请求者的唯一标识',
  `pname` varchar(32) NOT NULL,
  `renown` int(11) NOT NULL COMMENT '声望',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan_request`
--


-- --------------------------------------------------------

--
-- 表的结构 `notice`
--

CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svid` int(11) NOT NULL COMMENT '0表示所以服务器',
  `content` text NOT NULL,
  `begintime` bigint(20) NOT NULL,
  `endtime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `notice`
--


-- --------------------------------------------------------

--
-- 表的结构 `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL COMMENT '唯一标识',
  `chid` varchar(64) NOT NULL COMMENT '渠道充值的唯一标识',
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `pcid` int(11) NOT NULL COMMENT '客户端角色id',
  `channel` varchar(32) NOT NULL COMMENT '渠道',
  `money` double NOT NULL COMMENT '金额',
  `status` int(11) NOT NULL COMMENT '状态0创建',
  `gem` int(11) NOT NULL COMMENT '获得宝石数量',
  `createtime` datetime NOT NULL,
  `finishtime` bigint(20) NOT NULL COMMENT '完成时间',
  `productUUID` varchar(32) NOT NULL COMMENT '产品唯一标识',
  `query` text NOT NULL COMMENT '充值回掉参数',
  `phoneinfo` text NOT NULL COMMENT '手机mac,ip,imei',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unid` (`unid`,`svcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `payment`
--


-- --------------------------------------------------------

--
-- 表的结构 `payment_total`
--

CREATE TABLE IF NOT EXISTS `payment_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `channel` varchar(32) NOT NULL COMMENT '渠道',
  `january` int(11) NOT NULL COMMENT '一月',
  `february` int(11) NOT NULL COMMENT '二月',
  `march` int(11) NOT NULL COMMENT '三月',
  `april` int(11) NOT NULL COMMENT '四月',
  `may` int(11) NOT NULL COMMENT '五月',
  `june` int(11) NOT NULL COMMENT '六月',
  `july` int(11) NOT NULL COMMENT '七月',
  `august` int(11) NOT NULL COMMENT '八月',
  `september` int(11) NOT NULL COMMENT '九月',
  `october` int(11) NOT NULL COMMENT '十月',
  `november` int(11) NOT NULL COMMENT '十一月',
  `december` int(11) NOT NULL COMMENT '十二月',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `payment_total`
--


-- --------------------------------------------------------

--
-- 表的结构 `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcid` int(11) NOT NULL COMMENT '角色本地ID',
  `ucid` int(11) NOT NULL COMMENT '用户ucid',
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `pname` varchar(32) NOT NULL,
  `type` int(11) NOT NULL COMMENT '对象类型',
  `state` int(11) NOT NULL COMMENT '角色状态',
  `channel` varchar(16) NOT NULL COMMENT '角色渠道',
  `icon` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `crystal` int(11) NOT NULL COMMENT '水晶',
  `renown` int(11) NOT NULL COMMENT '声望',
  `weekRenown` int(11) NOT NULL COMMENT '每周增减声望',
  `cur_npc` int(11) NOT NULL,
  `npcs` text NOT NULL,
  `all_money` int(11) NOT NULL,
  `last_money` int(11) NOT NULL,
  `last_pay_time` bigint(20) NOT NULL,
  `guid_step` int(11) NOT NULL,
  `clancid` varchar(16) NOT NULL COMMENT '工会clientId',
  `clanPost` int(11) NOT NULL COMMENT '联盟职位',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `cicon` int(11) NOT NULL COMMENT '联盟旗帜',
  `maxBuidId` int(11) NOT NULL COMMENT '建筑的客户端最大ID',
  `maxObstId` int(11) NOT NULL COMMENT '最大的障碍ID',
  `stored_oil` int(11) NOT NULL,
  `stored_gold` int(11) NOT NULL,
  `isOnline` bit(1) NOT NULL,
  `protectTime` bigint(20) NOT NULL COMMENT '保护时间',
  `maxAttMCId` int(11) NOT NULL COMMENT '战报客服端ID',
  `maxBONum` int(11) NOT NULL COMMENT '建筑序列数量',
  `curBONum` int(11) NOT NULL COMMENT '当前使用的建筑序列',
  `spells` text NOT NULL COMMENT '拥有的药水',
  `lastAddObst` bigint(20) NOT NULL COMMENT '最后一次添加时间',
  `mark` text NOT NULL COMMENT '成就',
  `curtownlvl` int(11) NOT NULL COMMENT '当前城镇等级',
  `loginDay` int(11) NOT NULL COMMENT '连续登录的天数',
  `lastLoginTime` bigint(20) NOT NULL COMMENT '上一次登录时间',
  `isRewardDay` bit(1) NOT NULL COMMENT '是否领取每日奖励',
  `firstPayStatus` int(11) NOT NULL COMMENT '1表示首充成功未领奖',
  `pieceHPNum` int(11) NOT NULL COMMENT '生命碎片数量',
  `pieceDamNum` int(11) NOT NULL COMMENT '攻击力碎片',
  `pieceAttSpeed` int(11) NOT NULL COMMENT '攻击速度碎片',
  `dayTasks` text NOT NULL COMMENT '每日任务',
  `lastLeaveClan` bigint(20) NOT NULL COMMENT '离开联盟的时间',
  `monthCode` bigint(20) NOT NULL COMMENT '月卡结束时间点',
  `isMonCode` bit(1) NOT NULL COMMENT '是否已领当天的月卡',
  `builds` text NOT NULL COMMENT '建筑的bcid',
  `obstes` text NOT NULL COMMENT '障碍的ocid',
  `teches` text NOT NULL COMMENT '科技的tcid',
  `heroes` text NOT NULL COMMENT '英雄的hcid',
  `share` int(11) NOT NULL COMMENT '分享次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_armys`
--

CREATE TABLE IF NOT EXISTS `player_armys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `army_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色本地ID',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `bcid` int(11) NOT NULL COMMENT '建筑本地ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_armys`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_build_obst`
--

CREATE TABLE IF NOT EXISTS `player_build_obst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bcid` int(11) NOT NULL COMMENT 'clientId',
  `building_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `cooldown_ms` bigint(20) NOT NULL COMMENT '结束的时间点',
  `position_index` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_build_obst`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_buildings`
--

CREATE TABLE IF NOT EXISTS `player_buildings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bcid` int(11) NOT NULL COMMENT '建筑的clientId',
  `building_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `cooldown_ms` bigint(20) NOT NULL,
  `position_index` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `cur_produce_res` bigint(20) NOT NULL,
  `res_produce_begin_time` bigint(20) NOT NULL COMMENT '资源生产开始时间',
  `cur_up_tech_gid` int(11) NOT NULL,
  `end_tech_up_time` bigint(20) NOT NULL COMMENT '科技升级完成时间',
  `begin_army_time` bigint(20) NOT NULL COMMENT '开始造兵时间单位毫秒',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_buildings`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_hero`
--

CREATE TABLE IF NOT EXISTS `player_hero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hcid` int(11) NOT NULL COMMENT '英雄clientid',
  `pcid` int(11) NOT NULL COMMENT '拥有者clientId',
  `pname` varchar(32) NOT NULL,
  `hname` varchar(32) NOT NULL COMMENT '英雄名称',
  `hgid` int(11) NOT NULL COMMENT '英雄gid',
  `addDamage` int(11) NOT NULL COMMENT '已经增加的伤害',
  `maxDamage` int(11) NOT NULL COMMENT '当前主基地等级所对应的最大可增加伤害',
  `addHP` int(11) NOT NULL COMMENT '血量',
  `maxHP` int(11) NOT NULL,
  `addAttSpeed` int(11) NOT NULL COMMENT '攻击速度',
  `maxAttSpeed` int(11) NOT NULL,
  `statues` int(11) NOT NULL COMMENT '状态1死亡,0未死亡',
  `createTime` bigint(20) NOT NULL COMMENT '购买时间',
  `deadTime` bigint(20) NOT NULL COMMENT '死亡时间点',
  `skillGid` int(11) NOT NULL COMMENT '技能GID',
  `fpos` int(11) NOT NULL COMMENT '战斗开始所在位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_hero`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_mail`
--

CREATE TABLE IF NOT EXISTS `player_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mcid` int(11) NOT NULL COMMENT '邮件的clientId',
  `type` int(11) NOT NULL COMMENT '类型',
  `title` varchar(126) NOT NULL,
  `content` text NOT NULL COMMENT '内容',
  `create_time` bigint(20) NOT NULL,
  `fromId` int(11) NOT NULL COMMENT '角色clientId',
  `fromName` varchar(32) NOT NULL,
  `toId` int(11) NOT NULL COMMENT '角色clientId',
  `toName` varchar(32) NOT NULL,
  `isRead` bit(1) NOT NULL COMMENT '是否已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_mail`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_producting`
--

CREATE TABLE IF NOT EXISTS `player_producting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `bcid` int(11) NOT NULL COMMENT '建筑clientId',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_producting`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_renown`
--

CREATE TABLE IF NOT EXISTS `player_renown` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '用户clientId',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `pname` varchar(32) NOT NULL COMMENT '角色名称',
  `renown` int(11) NOT NULL COMMENT '角色当前的声望值',
  `weekRenown` int(11) NOT NULL COMMENT '周声望',
  `curtownlvl` int(11) NOT NULL COMMENT '当前城镇等级',
  `isOnline` bit(1) NOT NULL COMMENT '是否在线',
  `isNpc` bit(1) NOT NULL COMMENT 'NPC玩家',
  `protectTime` bigint(20) NOT NULL COMMENT '保护时间',
  `guidStep` int(11) NOT NULL COMMENT '新手步骤',
  `ccid` varchar(16) NOT NULL COMMENT '联盟标识',
  `lastLogin` bigint(20) NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_renown`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_tech`
--

CREATE TABLE IF NOT EXISTS `player_tech` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tech_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_tech`
--


-- --------------------------------------------------------

--
-- 表的结构 `rank_clan`
--

CREATE TABLE IF NOT EXISTS `rank_clan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `icon` int(11) NOT NULL COMMENT '联盟旗帜',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `currank` int(11) NOT NULL COMMENT '当前名次',
  `renownAll` int(11) NOT NULL COMMENT '总荣耀',
  `renownWeek` int(11) NOT NULL COMMENT '周排行榜',
  `type` int(11) NOT NULL COMMENT '0周1总',
  `curnum` int(11) NOT NULL COMMENT '当前人数',
  `maxnum` int(11) NOT NULL COMMENT '最大人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `rank_clan`
--


-- --------------------------------------------------------

--
-- 表的结构 `rank_player`
--

CREATE TABLE IF NOT EXISTS `rank_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '用户帐号唯一标识',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `pname` varchar(32) NOT NULL COMMENT '角色名',
  `pexp` int(11) NOT NULL COMMENT '此刻的经验',
  `clancid` varchar(16) NOT NULL COMMENT '工会clientID',
  `clanIcon` int(11) NOT NULL COMMENT 'icon',
  `clanName` varchar(32) NOT NULL COMMENT '工会名',
  `currank` int(11) NOT NULL COMMENT '当前名次',
  `renown` int(11) NOT NULL COMMENT '当前的声望',
  `weekAddRenown` int(11) NOT NULL COMMENT '周增加声望',
  `type` int(11) NOT NULL COMMENT '0周排行,1总排行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `rank_player`
--


-- --------------------------------------------------------

--
-- 表的结构 `reward`
--

CREATE TABLE IF NOT EXISTS `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcid` int(11) NOT NULL COMMENT '领奖玩家clientid',
  `pname` varchar(32) NOT NULL COMMENT '玩家名称',
  `type` int(11) NOT NULL COMMENT '奖励类型[0是个人,1是全服]',
  `reward` text NOT NULL COMMENT '奖励内容',
  `createtime` bigint(20) NOT NULL COMMENT '领奖时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `reward`
--


-- --------------------------------------------------------

--
-- 表的结构 `servers`
--

CREATE TABLE IF NOT EXISTS `servers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(32) NOT NULL,
  `svip` varchar(32) NOT NULL,
  `port` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `version` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `servers`
--


-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '本地ID',
  `login_pwd` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `login_uid` varchar(64) NOT NULL,
  `uuid` varchar(64) NOT NULL,
  `login_time` bigint(20) NOT NULL,
  `model` varchar(128) NOT NULL COMMENT '机型',
  `version` varchar(32) NOT NULL COMMENT '客服端ver',
  `remain` varchar(32) NOT NULL,
  `pcids` text NOT NULL COMMENT '该用户下面的所有角色列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `user`
--

--
-- 数据库: `seawar2_design`
--
CREATE DATABASE `seawar2_design` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `seawar2_design`;

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库主键标识',
  `name` varchar(16) NOT NULL COMMENT '名字',
  `lgID` varchar(16) NOT NULL COMMENT '登录帐号',
  `lgPwd` varchar(16) NOT NULL COMMENT '登录密码',
  `post` int(11) NOT NULL COMMENT '职位',
  `powers` varchar(32) NOT NULL COMMENT '权限(运行的操作)',
  `lastLogin` datetime NOT NULL COMMENT '最后登录时间',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `lgID` (`lgID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `admin`
--


-- --------------------------------------------------------

--
-- 表的结构 `attack_defense_info`
--

CREATE TABLE IF NOT EXISTS `attack_defense_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attmcid` varchar(32) NOT NULL COMMENT '战报clientId',
  `builds` text NOT NULL COMMENT '防御方建筑信息',
  `obsts` text NOT NULL COMMENT '障碍',
  `amrys` text NOT NULL COMMENT '防御方兵信息',
  `teches` text NOT NULL,
  `heros` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `attmail_id` (`attmcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `attack_defense_info`
--


-- --------------------------------------------------------

--
-- 表的结构 `attack_mail`
--

CREATE TABLE IF NOT EXISTS `attack_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attcid` varchar(32) NOT NULL COMMENT '战报clientId',
  `attPcid` int(11) NOT NULL COMMENT '攻击者clientId',
  `attPname` varchar(32) NOT NULL COMMENT '攻击者名称',
  `beAttPcid` int(11) NOT NULL COMMENT '被攻击者clientId',
  `beAttPname` varchar(32) NOT NULL COMMENT '被攻击者名称',
  `begin_time` bigint(20) NOT NULL COMMENT '战斗开始时间',
  `end_time` bigint(20) NOT NULL COMMENT '战斗结束时间',
  `star` int(11) NOT NULL COMMENT '攻击所打星数',
  `preGold` int(11) NOT NULL COMMENT '攻击者攻击前的金币',
  `preOil` int(11) NOT NULL COMMENT '攻击者被攻击前的油',
  `preAttRenown` int(11) NOT NULL COMMENT '攻击前攻击者的声望',
  `attRenown` int(11) NOT NULL COMMENT '攻击获得或失去的声望值',
  `attGold` int(11) NOT NULL COMMENT '攻击获得金币',
  `attOil` int(11) NOT NULL COMMENT '攻击获得的油',
  `isHitBack` bit(1) NOT NULL COMMENT '被攻击者是否反击',
  `clancid` varchar(16) NOT NULL COMMENT '攻击者当时的联盟标识',
  `cname` varchar(32) NOT NULL COMMENT '攻击者联盟名称',
  `cicon` int(11) NOT NULL COMMENT '攻击者联盟图标',
  `offenHP` int(11) NOT NULL COMMENT '攻击方所加点数HP',
  `offenAtt` int(11) NOT NULL COMMENT '攻击方所加攻击力点数',
  `defccid` varchar(16) NOT NULL COMMENT '防御方联盟标识',
  `defcname` varchar(32) NOT NULL COMMENT '防御方联盟名称',
  `defcicon` int(11) NOT NULL COMMENT '防御方联盟图标',
  `defenseHP` int(11) NOT NULL,
  `defenseAtt` int(11) NOT NULL COMMENT '防御方所加攻击力数',
  `attInfos` text NOT NULL COMMENT '攻击信息(放的兵位置，时间等等)',
  `beRewon` int(11) NOT NULL COMMENT '被攻击者获得失去声望',
  `egid` int(11) NOT NULL COMMENT '能源片gid',
  `num` int(11) NOT NULL COMMENT '能源片数量',
  `attHeros` text NOT NULL COMMENT '攻击者英雄信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `attcid` (`attcid`),
  KEY `beAttPid` (`beAttPcid`),
  KEY `attPid` (`attPcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `attack_mail`
--


-- --------------------------------------------------------

--
-- 表的结构 `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` int(11) NOT NULL COMMENT '聊天clientId',
  `type` int(11) NOT NULL COMMENT '聊天类型',
  `content` text NOT NULL COMMENT '内容',
  `create_time` bigint(20) NOT NULL,
  `fromId` int(11) NOT NULL,
  `fromName` varchar(32) NOT NULL,
  `toId` int(11) NOT NULL,
  `toName` varchar(32) NOT NULL,
  `clancid` varchar(16) NOT NULL COMMENT '联盟聊天用的',
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  KEY `type_2` (`type`,`toName`),
  KEY `type_3` (`type`,`fromName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `chat`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan`
--

CREATE TABLE IF NOT EXISTS `clan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '工会clientId',
  `clan_name` varchar(32) NOT NULL,
  `icon` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `desc` text NOT NULL,
  `maxNum` int(11) NOT NULL COMMENT '最大人数',
  `curNum` int(11) NOT NULL COMMENT '当前数量',
  `create_time` bigint(20) NOT NULL,
  `pointHP` int(11) NOT NULL COMMENT '加生命点数(1点代表xx点血)',
  `pointAtt` int(11) NOT NULL COMMENT '攻击力点数(1点等于xx点)',
  `renownAll` int(11) NOT NULL COMMENT '总声望',
  `renownWeek` int(11) NOT NULL COMMENT '周排行',
  `nextHPGold` bigint(20) NOT NULL,
  `nextAttOil` bigint(20) NOT NULL,
  `curDonateGold` bigint(20) NOT NULL COMMENT '当前点数所捐金',
  `curDonateOil` bigint(20) NOT NULL COMMENT '当前点数所捐油',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ccid` (`ccid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan_member`
--

CREATE TABLE IF NOT EXISTS `clan_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `ucid` int(11) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '玩家唯一标识',
  `pname` varchar(32) NOT NULL COMMENT '玩家名称',
  `post` int(11) NOT NULL COMMENT '职位',
  `donateGold` int(11) NOT NULL COMMENT '捐献金币总数',
  `donateOil` int(11) NOT NULL COMMENT '捐献油总数',
  `curDGold` int(11) NOT NULL COMMENT '当前点数所捐金币',
  `curDOil` int(11) NOT NULL COMMENT '当前点数所捐的油',
  `lastDGold` bigint(20) NOT NULL COMMENT '最后一次捐献金币时间',
  `lastDOil` bigint(20) NOT NULL COMMENT '最后一次捐赠油时间',
  `renownAll` int(11) NOT NULL COMMENT '个人总荣耀',
  `renownWeek` int(11) NOT NULL COMMENT '周排行榜',
  `daynumgold` int(11) NOT NULL COMMENT '当天捐金次数',
  `daynumoil` int(11) NOT NULL COMMENT '当天捐油次数',
  `maxdaynum` int(11) NOT NULL COMMENT '当天可捐的最大次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pcid` (`pcid`),
  KEY `ccid` (`ccid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan_member`
--


-- --------------------------------------------------------

--
-- 表的结构 `clan_request`
--

CREATE TABLE IF NOT EXISTS `clan_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `pcid` int(11) NOT NULL COMMENT '请求者的唯一标识',
  `pname` varchar(32) NOT NULL,
  `renown` int(11) NOT NULL COMMENT '声望',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ccid` (`ccid`,`pcid`),
  KEY `pcid` (`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `clan_request`
--


-- --------------------------------------------------------

--
-- 表的结构 `notice`
--

CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svid` int(11) NOT NULL COMMENT '0表示所以服务器',
  `content` text NOT NULL,
  `begintime` bigint(20) NOT NULL,
  `endtime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `notice`
--


-- --------------------------------------------------------

--
-- 表的结构 `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unid` varchar(64) NOT NULL COMMENT '唯一标识',
  `chid` varchar(64) NOT NULL COMMENT '渠道充值的唯一标识',
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `pcid` int(11) NOT NULL COMMENT '客户端角色id',
  `channel` varchar(32) NOT NULL COMMENT '渠道',
  `money` double NOT NULL COMMENT '金额',
  `status` int(11) NOT NULL COMMENT '状态0创建',
  `gem` int(11) NOT NULL COMMENT '获得宝石数量',
  `createtime` datetime NOT NULL,
  `finishtime` bigint(20) NOT NULL COMMENT '完成时间',
  `productUUID` varchar(32) NOT NULL COMMENT '产品唯一标识',
  `query` text NOT NULL COMMENT '充值回掉参数',
  `phoneinfo` text NOT NULL COMMENT '手机mac,ip,imei',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unid` (`unid`,`svcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `payment`
--


-- --------------------------------------------------------

--
-- 表的结构 `payment_total`
--

CREATE TABLE IF NOT EXISTS `payment_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `channel` varchar(32) NOT NULL COMMENT '渠道',
  `january` int(11) NOT NULL COMMENT '一月',
  `february` int(11) NOT NULL COMMENT '二月',
  `march` int(11) NOT NULL COMMENT '三月',
  `april` int(11) NOT NULL COMMENT '四月',
  `may` int(11) NOT NULL COMMENT '五月',
  `june` int(11) NOT NULL COMMENT '六月',
  `july` int(11) NOT NULL COMMENT '七月',
  `august` int(11) NOT NULL COMMENT '八月',
  `september` int(11) NOT NULL COMMENT '九月',
  `october` int(11) NOT NULL COMMENT '十月',
  `november` int(11) NOT NULL COMMENT '十一月',
  `december` int(11) NOT NULL COMMENT '十二月',
  PRIMARY KEY (`id`),
  UNIQUE KEY `svcid` (`svcid`,`channel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `payment_total`
--


-- --------------------------------------------------------

--
-- 表的结构 `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcid` int(11) NOT NULL COMMENT '角色本地ID',
  `ucid` int(11) NOT NULL COMMENT '用户ucid',
  `svcid` int(11) NOT NULL COMMENT '服务器clientId',
  `pname` varchar(32) NOT NULL,
  `type` int(11) NOT NULL COMMENT '对象类型',
  `state` int(11) NOT NULL COMMENT '角色状态',
  `channel` varchar(16) NOT NULL COMMENT '角色渠道',
  `icon` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `crystal` int(11) NOT NULL COMMENT '水晶',
  `renown` int(11) NOT NULL COMMENT '声望',
  `weekRenown` int(11) NOT NULL COMMENT '每周增减声望',
  `cur_npc` int(11) NOT NULL,
  `npcs` text NOT NULL,
  `all_money` int(11) NOT NULL,
  `last_money` int(11) NOT NULL,
  `last_pay_time` bigint(20) NOT NULL,
  `guid_step` int(11) NOT NULL,
  `clancid` varchar(16) NOT NULL COMMENT '工会clientId',
  `clanPost` int(11) NOT NULL COMMENT '联盟职位',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `cicon` int(11) NOT NULL COMMENT '联盟旗帜',
  `maxBuidId` int(11) NOT NULL COMMENT '建筑的客户端最大ID',
  `maxObstId` int(11) NOT NULL COMMENT '最大的障碍ID',
  `stored_oil` int(11) NOT NULL,
  `stored_gold` int(11) NOT NULL,
  `isOnline` bit(1) NOT NULL,
  `protectTime` bigint(20) NOT NULL COMMENT '保护时间',
  `maxAttMCId` int(11) NOT NULL COMMENT '战报客服端ID',
  `maxBONum` int(11) NOT NULL COMMENT '建筑序列数量',
  `curBONum` int(11) NOT NULL COMMENT '当前使用的建筑序列',
  `spells` text NOT NULL COMMENT '拥有的药水',
  `lastAddObst` bigint(20) NOT NULL COMMENT '最后一次添加时间',
  `mark` text NOT NULL COMMENT '成就',
  `curtownlvl` int(11) NOT NULL COMMENT '当前城镇等级',
  `loginDay` int(11) NOT NULL COMMENT '连续登录的天数',
  `lastLoginTime` bigint(20) NOT NULL COMMENT '上一次登录时间',
  `isRewardDay` bit(1) NOT NULL COMMENT '是否领取每日奖励',
  `firstPayStatus` int(11) NOT NULL COMMENT '1表示首充成功未领奖',
  `pieceHPNum` int(11) NOT NULL COMMENT '生命碎片数量',
  `pieceDamNum` int(11) NOT NULL COMMENT '攻击力碎片',
  `pieceAttSpeed` int(11) NOT NULL COMMENT '攻击速度碎片',
  `dayTasks` text NOT NULL COMMENT '每日任务',
  `lastLeaveClan` bigint(20) NOT NULL COMMENT '离开联盟的时间',
  `monthCode` bigint(20) NOT NULL COMMENT '月卡结束时间点',
  `isMonCode` bit(1) NOT NULL COMMENT '是否已领当天的月卡',
  `builds` text NOT NULL COMMENT '建筑的bcid',
  `obstes` text NOT NULL COMMENT '障碍的ocid',
  `teches` text NOT NULL COMMENT '科技的tcid',
  `heroes` text NOT NULL COMMENT '英雄的hcid',
  `share` int(11) NOT NULL COMMENT '分享次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pname` (`pname`),
  UNIQUE KEY `pcid` (`pcid`),
  KEY `uid` (`ucid`,`svcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_armys`
--

CREATE TABLE IF NOT EXISTS `player_armys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `army_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色本地ID',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `bcid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `player_id_2` (`pcid`,`gid`,`bcid`),
  KEY `player_id` (`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_armys`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_build_obst`
--

CREATE TABLE IF NOT EXISTS `player_build_obst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bcid` int(11) NOT NULL,
  `building_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL,
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `cooldown_ms` bigint(20) NOT NULL COMMENT '结束的时间点',
  `position_index` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id` (`bcid`,`pcid`),
  KEY `player_id` (`pcid`),
  KEY `player_id_2` (`pcid`,`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_build_obst`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_buildings`
--

CREATE TABLE IF NOT EXISTS `player_buildings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bcid` int(11) NOT NULL COMMENT '建筑的clientId',
  `building_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  `cooldown_ms` bigint(20) NOT NULL COMMENT '结束的时间点',
  `position_index` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `cur_produce_res` bigint(20) NOT NULL,
  `res_produce_begin_time` bigint(20) NOT NULL COMMENT '资源生产开始时间',
  `cur_up_tech_gid` int(11) NOT NULL,
  `end_tech_up_time` bigint(20) NOT NULL COMMENT '科技升级完成时间',
  `begin_army_time` bigint(20) NOT NULL COMMENT '开始造兵时间单位毫秒',
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id` (`bcid`,`pcid`),
  KEY `player_id` (`pcid`),
  KEY `player_id_gid` (`pcid`,`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_buildings`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_hero`
--

CREATE TABLE IF NOT EXISTS `player_hero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hcid` int(11) NOT NULL COMMENT '英雄clientid',
  `pcid` int(11) NOT NULL COMMENT '拥有者clientId',
  `pname` varchar(32) NOT NULL,
  `hname` varchar(32) NOT NULL COMMENT '英雄名称',
  `hgid` int(11) NOT NULL COMMENT '英雄gid',
  `addDamage` int(11) NOT NULL COMMENT '已经增加的伤害',
  `maxDamage` int(11) NOT NULL COMMENT '当前主基地等级所对应的最大可增加伤害',
  `addHP` int(11) NOT NULL COMMENT '血量',
  `maxHP` int(11) NOT NULL,
  `addAttSpeed` int(11) NOT NULL COMMENT '攻击速度',
  `maxAttSpeed` int(11) NOT NULL,
  `statues` int(11) NOT NULL COMMENT '状态1死亡,0未死亡',
  `createTime` bigint(20) NOT NULL COMMENT '购买时间',
  `deadTime` bigint(20) NOT NULL COMMENT '死亡时间点',
  `skillGid` int(11) NOT NULL COMMENT '技能GID',
  `fpos` int(11) NOT NULL COMMENT '战斗开始所在位置',
  PRIMARY KEY (`id`),
  UNIQUE KEY `hcid` (`hcid`),
  UNIQUE KEY `pcid` (`pcid`,`hgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_hero`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_mail`
--

CREATE TABLE IF NOT EXISTS `player_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mcid` int(11) NOT NULL COMMENT '邮件的clientId',
  `type` int(11) NOT NULL COMMENT '类型',
  `title` varchar(126) NOT NULL,
  `content` text NOT NULL COMMENT '内容',
  `create_time` bigint(20) NOT NULL,
  `fromId` int(11) NOT NULL COMMENT '角色clientId',
  `fromName` varchar(32) NOT NULL,
  `toId` int(11) NOT NULL COMMENT '角色clientId',
  `toName` varchar(32) NOT NULL,
  `isRead` bit(1) NOT NULL COMMENT '是否已读',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mcid` (`mcid`),
  KEY `type` (`type`),
  KEY `type_2` (`type`,`fromId`),
  KEY `type_3` (`type`,`toId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_mail`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_producting`
--

CREATE TABLE IF NOT EXISTS `player_producting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `bcid` int(11) NOT NULL COMMENT '建筑clientId',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gid` (`gid`,`bcid`,`pcid`),
  KEY `building_id` (`bcid`,`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_producting`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_renown`
--

CREATE TABLE IF NOT EXISTS `player_renown` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '用户clientId',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `pname` varchar(32) NOT NULL COMMENT '角色名称',
  `renown` int(11) NOT NULL COMMENT '角色当前的声望值',
  `weekRenown` int(11) NOT NULL COMMENT '周声望',
  `curtownlvl` int(11) NOT NULL COMMENT '当前城镇等级',
  `isOnline` bit(1) NOT NULL COMMENT '是否在线',
  `isNpc` bit(1) NOT NULL COMMENT 'NPC玩家',
  `protectTime` bigint(20) NOT NULL COMMENT '保护时间',
  `guidStep` int(11) NOT NULL COMMENT '新手步骤',
  `ccid` varchar(16) NOT NULL COMMENT '联盟标识',
  `lastLogin` bigint(20) NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pid` (`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_renown`
--


-- --------------------------------------------------------

--
-- 表的结构 `player_tech`
--

CREATE TABLE IF NOT EXISTS `player_tech` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tech_name` varchar(32) NOT NULL,
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `player_name` varchar(32) NOT NULL,
  `gid` int(11) NOT NULL,
  `lvl` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `player_id_2` (`pcid`,`gid`),
  KEY `player_id` (`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `player_tech`
--


-- --------------------------------------------------------

--
-- 表的结构 `rank_clan`
--

CREATE TABLE IF NOT EXISTS `rank_clan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccid` varchar(16) NOT NULL COMMENT '联盟唯一标识',
  `icon` int(11) NOT NULL COMMENT '联盟旗帜',
  `cname` varchar(32) NOT NULL COMMENT '联盟名称',
  `currank` int(11) NOT NULL COMMENT '当前名次',
  `renownAll` int(11) NOT NULL COMMENT '总荣耀',
  `renownWeek` int(11) NOT NULL COMMENT '周排行榜',
  `type` int(11) NOT NULL COMMENT '0周1总',
  `curnum` int(11) NOT NULL COMMENT '当前人数',
  `maxnum` int(11) NOT NULL COMMENT '最大人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `rank_clan`
--


-- --------------------------------------------------------

--
-- 表的结构 `rank_player`
--

CREATE TABLE IF NOT EXISTS `rank_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '用户帐号唯一标识',
  `pcid` int(11) NOT NULL COMMENT '角色clientId',
  `pname` varchar(32) NOT NULL COMMENT '角色名',
  `pexp` int(11) NOT NULL COMMENT '此刻的经验',
  `clancid` varchar(16) NOT NULL COMMENT '工会clientID',
  `clanIcon` int(11) NOT NULL COMMENT 'icon',
  `clanName` varchar(32) NOT NULL COMMENT '工会名',
  `currank` int(11) NOT NULL COMMENT '当前名次',
  `renown` int(11) NOT NULL COMMENT '当前的声望',
  `weekAddRenown` int(11) NOT NULL COMMENT '周增加声望',
  `type` int(11) NOT NULL COMMENT '0周排行,1总排行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `rank_player`
--


-- --------------------------------------------------------

--
-- 表的结构 `reward`
--

CREATE TABLE IF NOT EXISTS `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcid` int(11) NOT NULL COMMENT '领奖玩家clientid',
  `pname` varchar(32) NOT NULL COMMENT '玩家名称',
  `type` int(11) NOT NULL COMMENT '奖励类型[0是个人,1是全服]',
  `reward` text NOT NULL COMMENT '奖励内容',
  `createtime` bigint(20) NOT NULL COMMENT '领奖时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `reward`
--


-- --------------------------------------------------------

--
-- 表的结构 `servers`
--

CREATE TABLE IF NOT EXISTS `servers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(32) NOT NULL,
  `svip` varchar(32) NOT NULL,
  `port` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `version` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `servers`
--


-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` int(11) NOT NULL COMMENT '本地ID',
  `login_pwd` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `login_uid` varchar(64) NOT NULL,
  `uuid` varchar(64) NOT NULL,
  `login_time` bigint(20) NOT NULL,
  `model` varchar(128) NOT NULL COMMENT '机型',
  `version` varchar(32) NOT NULL COMMENT '客服端ver',
  `remain` varchar(32) NOT NULL,
  `pcids` text NOT NULL COMMENT '该用户下面的所有角色列表',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_uid` (`login_uid`),
  UNIQUE KEY `login_pwd` (`login_pwd`,`login_uid`),
  UNIQUE KEY `ucid` (`ucid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `user`
--

--
-- 数据库: `seawar2_log`
--
CREATE DATABASE `seawar2_log` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `seawar2_log`;
