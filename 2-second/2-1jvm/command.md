linux�������  
env; �鿴ȫ�ֵĻ���������JVM�������ÿ��ܻ�����һЩ����������  
echo $������; �鿴��������������ֵ  
lsof -i:�˿ں�; �鿴�˿������ĸ�����  
ps -ef|grep {PID �� ������Java��tomcat}; ��ѯ����Ľ���
[ps�������](https://www.cnblogs.com/moonbaby/p/10528443.html)
top������ʾ��ǰ�����̵�cpu����

jvm�������[���](https://blog.csdn.net/u010316188/article/details/80215884)  
jinfo;�����������Ϣ  
jps;��ʾ���������  
jstat; ���������ʱ������ϸ����  
jmap; �ڴ����  
jstack; �߳̿���



## JVM����
### ����jvisualvmԶ�����ӣ�JVM�������
-Dcom.sun.management.jmxremote.port=1090\##�˿ںſ����Լ�ָ��
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
