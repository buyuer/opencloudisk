var g_url;

var test_url = [
    "{\"path\":\"/\",\"info\":[[\"$RECYCLE.BIN\",\"D\"],[\".bash_history\",\"F\"],[\".cache\",\"D\"],[\".config\",\"D\"],[\".pki\",\"D\"],[\".thumbnails\",\"D\"],[\"Downloads\",\"D\"],[\"found.000\",\"D\"],[\"System Volume Information\",\"D\"],[\"test.zip\",\"F\"],[\"UPRE技术文档\",\"D\"],[\"云盘目录把保存的文件路径填写到这里方便查找.txt\",\"F\"],[\"其他赛事\",\"D\"],[\"历年各代车介绍\",\"D\"],[\"各届队员联系表\",\"D\"],[\"学习资料\",\"D\"],[\"实验室暑假培训\",\"D\"],[\"宣传、招新、队服\",\"D\"],[\"影像留念\",\"D\"],[\"机器人预备队\",\"D\"],[\"论文\",\"D\"],[\"门禁及签到系统\",\"D\"]]}",
    "{\"path\":\"/firefly\",\"info\":[[\"Pictures\",\"D\"],[\"Downloads\",\"D\"],[\".xscreensaver\",\"F\"],[\".config\",\"D\"],[\".sudo_as_admin_successful\",\"F\"],[\".profile\",\"F\"],[\"Desktop\",\"D\"],[\"Videos\",\"D\"],[\"Templates\",\"D\"],[\".viminfo\",\"F\"],[\"Public\",\"D\"],[\".pki\",\"D\"],[\".bash_history\",\"F\"],[\".bash_logout\",\"F\"],[\".cache\",\"D\"],[\"Documents\",\"D\"],[\".gnupg\",\"D\"],[\".Xauthority\",\"F\"],[\".bashrc\",\"F\"],[\".xsession-errors.old\",\"F\"],[\".local\",\"D\"],[\".dmrc\",\"F\"],[\"Music\",\"D\"],[\".python_history\",\"F\"],[\".thumbnails\",\"D\"],[\".xsession-errors\",\"F\"]]}",
    "{\"path\":\"/历年各代车介绍\",\"info\":[[\"2009\",\"D\"],[\"2010\",\"D\"],[\"2011\",\"D\"],[\"2012\",\"D\"],[\"2013\",\"D\"],[\"2014\",\"D\"],[\"2015\",\"D\"],[\"2016\",\"D\"],[\"2017\",\"D\"]]}",
    "{\"path\":\"/System Volume Information\",\"info\":[[\"Chkdsk\",\"D\"],[\"IndexerVolumeGuid\",\"F\"],[\"tracking.log\",\"F\"],[\"WPSettings.dat\",\"F\"]]}",
    "{\"path\":\"/门禁及签到系统\",\"info\":[[\"旧门禁及签到整理\",\"D\"]]}",
    "{\"path\":\"/123/abc/def\",\"info\":[]}",
    "{\"path\":\"\",\"info\":[[\"$RECYCLE.BIN\",\"D\",\"0\",\"\"],[\"Altium_Designer\",\"D\",\"8192\",\"\"],[\"ARM_delelopment_suite\",\"D\",\"4096\",\"\"],[\"Bootstrap\",\"D\",\"0\",\"\"],[\"Config.Msi\",\"D\",\"0\",\"\"],[\"Evenote\",\"D\",\"28672\",\"\"],[\"Git\",\"D\",\"0\",\"\"],[\"Gradle_Tool\",\"D\",\"0\",\"\"],[\"Jetbrains\",\"D\",\"0\",\"\"],[\"Microsoft VS Code\",\"D\",\"28672\",\"\"],[\"Mind_Master\",\"D\",\"0\",\"\"],[\"Program Files (x86)\",\"D\",\"0\",\"\"],[\"ProgramData\",\"D\",\"0\",\"\"],[\"QQ\",\"D\",\"4096\",\"\"],[\"System Volume Information\",\"D\",\"4096\",\"\"],[\"Typora\",\"D\",\"0\",\"\"],[\"Visual_Studio_2019\",\"D\",\"4096\",\"\"],[\"Windows Kits\",\"D\",\"0\",\"\"]]}"
];

var data_format = {
    path:'null',
    info:[['null','null','null','null'],['null','null','null','null']]
};

var Data_Process = (str)=> {
    g_url = JSON.parse(str);
    return g_url;
};

var test_addry= () =>{
    var xmlhttp;
    if(window.XMLHttpRequest)
    {
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=()=>{
        if(xmlhttp.readyState===4 && xmlhttp.status===200)
        {
            //alert(xmlhttp.responseText.toString());
            var string =  xmlhttp.responseText;
           return (test_url[6]);
        }
    };
    xmlhttp.open("GET","/fileinfo?path=",true);
    xmlhttp.send();
};
//将路径信息处理为数据
var Str_Process=(str)=>{
    var array=[];
    var str_temp=str;
    var j = 0;
    g_url = str + '/';
    while(1)
    {
        var num = str.indexOf('/');
        if(num===0)
        {
            str = str.substring(1,str.length);
        }
        else if(num !== -1)
        {
            array[j++] = str.substring(0,num);
            str = str.substring(num,str.length);
        }
        else
        {
            array[j++] = str.substring(0,str.length);
            break;
        }
    }
    return array;
};








