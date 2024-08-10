package com.mddb.usecase.impl;

import com.mddb.dao.MockRepository;
import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import com.mddb.usecase.DeviceService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mainbord on 07.11.17.
 */
@RequiredArgsConstructor
//@Service
public class DeviceServiceMock implements DeviceService {

    private final MockRepository repository = new MockRepository();
//    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceDto> getDevices(Integer pageNumber, Integer pageSize) {
        //        return repository.findAll()
//                .stream()
//                .map(deviceMapper::map)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public Set<String> getCompaniesNames() {
        return repository.getCompaniesNames();
    }

    @Override
    public Map<Integer, String> getDevicesByCompany(String company) {
        return repository.findByCompanyName(company);
    }

    @Override
    public Device getDevice(Long id) {
        return repository.getDevice(id);
    }

}
