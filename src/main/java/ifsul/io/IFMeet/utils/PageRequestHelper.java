package ifsul.io.IFMeet.utils;

import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PageRequestHelper {
    public PageRequest getPageRequest(DefaultRequestParams requestParams) {
        if (requestParams.getSortColumn() == null || requestParams.getSortDirection() == null) {
            return PageRequest.of(requestParams.getPageNumber(), requestParams.getPageSize());
        }
        return PageRequest.of(requestParams.getPageNumber(), requestParams.getPageSize(), requestParams.getSortDirection(), requestParams.getSortColumn());
    }
}
