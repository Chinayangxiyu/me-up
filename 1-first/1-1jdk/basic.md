# һ�����л���Serializable��
������Java���л�ʹ�÷��佫�������л�Ϊ���������ݣ���ʹ�õ�ʱ�����л�Ϊ����
[����](https://time.geekbang.org/column/article/99774?utm_source=pinpaizhuanqu&utm_medium=geektime&utm_campaign=guanwang&utm_term=guanwang&utm_content=0511)
## �ؼ���
1��Serializable�ӿڣ�ֻ��ʵ����Serializable�ӿڵ������ʵ�����л��ͷ����л���
2��serialVersionUID�����л�����İ汾�ţ��汾�Ų�ͬ�������л���ʧ�ܡ�
3��ObjectInputStream/ObjectOutputStream��ִ�����л��ͷ����л���
4��ע��transient����Ǹ����Բ��������л���
5��writeObject������readObject������Ĭ����ʹ��ObjectInputStream/ObjectOutputStream������������ִ�����л��ͷ����л��������Ҫ����
��ǰ����Ҫʹ��final ��дwriteObject��readObject�������������л���
6��writeReplace() �� readResolve()�����л�ǰ�÷�����Ԥ���������л����÷��������ô���

## ���ӷ���
���ƣ�
Java���ƻ������л���ʽ���ṩ�����Ƶ����л��������л���Ϣ����Ϊ���ơ�
���ƣ�
&emsp;&emsp;  ���ܿ�ƽ̨ʹ�ã�
&emsp;&emsp;  �����л�����ȫ��
&emsp;&emsp;  Java�����л�ʵ�ֱȽϸ��ӣ��ṩ��������Ϣ���������л����ռ��Դ���������л�ʱ��Ҳ����

**����Java���л������ƣ����ڻ���ʹ��fastJson��gson��Protobuf�������л�**

## ���л�����������
����ģʽ�ڽ��з����л�ʱ���Υ������ԭ�򣬿���ͨ����дreadResolve������֤�������ԡ�
����ģʽ���õķ�ʽ��ʹ��ö��ʵ�֣�ö����Ԥ�����䣬�����л����������ұ�֤������


# ����ö��