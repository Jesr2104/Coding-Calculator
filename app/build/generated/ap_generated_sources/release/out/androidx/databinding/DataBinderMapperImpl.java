package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.just_jump.coding_calculator.DataBinderMapperImpl());
  }
}
