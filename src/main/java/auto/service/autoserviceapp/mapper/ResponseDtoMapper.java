package auto.service.autoserviceapp.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
