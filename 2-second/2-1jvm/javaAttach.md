# ����
attach�����������������ƣ�JVM�ṩ��һ��JVM֮��**����ͨ��**������������һ�����̴����������һ�����̣�����ִ��һЩ�ڲ��Ĳ�����
Ӧ�ó�����ͨ��jstack���������ǰ���߳�dump��
com.sun.tools.attach��·������attach���API��

# ԭ��
    Unix domain socket ��IPC���Ƶ�һ��ʵ�ַ�ʽ��IPC��inter-process communication ���̼�ͨ��)���ǻ�������socket�ݽ����ģ�
����ͬһ̨�����Ľ��̼�ͨѶ��
ͨ�Ź����в���Ҫ��������Э��ջ������Ҫ������������У��͡�ά����ź�Ӧ��Ч�ʸ���
    attach���ǻ���IPCʵ�ֵ�JVM��ͨ�ţ�ÿ��JVM������Signal Dispatcher ��Attach Listener�����̡߳�

## Attach Listener��Ŀ��JVM��
&emsp;&emsp;   ������ᴴ��һ�������׽��֣���������һ���ļ�/tmp/.java_pid���ͻ��˺�Ŀ��JVM����ͨ�����socketFile����ͨ�š�
&emsp;&emsp;   Attach Listener�߳̿�����**��������**��ʱ�����JVM��������������StartAttachListener����
&emsp;&emsp;   ���Ŀ��JVM����ʱδ����Attach Listener�̣߳�**����ʱ**��Ҫ�������ͻ��˻���������JVM����һ���źţ�Ŀ��JVM��Signal Dispatcher�߳�
�������ڼ����źŵģ��������ź���"sigquit"ʱ��Signal Dispatcher������Attach Listener�̡߳�

## Signal Dispatcher
&emsp;&emsp;   ���������� OS�����̼�ͨ�ż�ƣ����źš�

����������
��ʽ���ݿ� HiStore
Exactly-Once����
��Դ�������StreamLib

