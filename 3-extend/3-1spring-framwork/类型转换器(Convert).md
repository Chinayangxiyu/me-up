# 1������ת���ײ������
Converter:������ת����
GenericConverter:ͨ�õġ����ġ�������ת������ʵ�ָ��ӣ���
ConditionalConverter:ת����������ƥ�䡣
ConversionService:������ת��ϵͳ����ڣ�Э������ת�����ĵ���.
ConverterRegistry:ת����ע������һ���ConversionService���ʹ�á�

ʵ�ʳ�����Converter��GenericConverter���Ի�����ã�  
����User��һ������ companys List<Company>,��ҪGenericConverterȥת����
�ڲ�Ԫ��Company ��ҪConverterȥת�������εݹ�,ֱ��ת��Ϊ�����͡�

# 2������ת����
TypeConverter:����ת�������Ľӿڣ������ṩ����ת�����ܡ�
TypeConverterSupport:TypeConverter�ĳ���ʵ�֣���Ҫ����BeanWrapperImpl�Ļ��ࡣ
TypeConverterDelegate:����ת����ί���ࣨ�����ࣩ���ڲ�ʹ��conversionService���ײ�����ת��������PropertyEditor�����Ա༭����  
ʵ�ֶ�Javabean�Ĳ�����
**BeanWrapperImpl�����վ���ͨ��TypeConverterDelegateʵ�����ԵĽ�������䡢ת������������������  
AbstractAutowireCapableBeanFactory.applyPropertyValues()**