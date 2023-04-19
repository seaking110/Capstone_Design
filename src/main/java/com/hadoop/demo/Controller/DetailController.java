package com.hadoop.demo.Controller;

import com.hadoop.demo.Model.*;
import com.hadoop.demo.Service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class DetailController {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    static class handleRequest{
        private Integer lastPart;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    static class handleRequest2{
        private Integer id;
    }

    @Autowired
    private CpuListService cpuListService;
    @Autowired
    private CpuDetailsService cpuDetailsService;

    @Autowired
    private GpuListService gpuListService;
    @Autowired
    private GpuDetailsService gpuDetailsService;

    @Autowired
    private GameListOriginService gameListOriginService;

    // id를 보내 한개의 cpu gpu detail 부분 요청 시
    @PostMapping("/find_cpu_details")
    public CpuDetails getCpuInfo(@RequestBody handleRequest id) throws IOException {
        String cpuName = cpuListService.findById(id.getLastPart()).getCpuName();
        return cpuDetailsService.findByName(cpuName);
    }

    @PostMapping("/find_cpu_details2")
    public CpuDetails getCpuInfo(@RequestBody handleRequest2 id) throws IOException {
        String cpuName = cpuListService.findById(id.getId()).getCpuName();
        return cpuDetailsService.findByName(cpuName);
    }

    @PostMapping("/find_gpu_details")
    public GpuDetails getGpuInfo(@RequestBody handleRequest id) throws IOException {
        String gpuName = gpuListService.findById(id.getLastPart()).getGpuName();
        return gpuDetailsService.findByName(gpuName);
    }

    // 모델명을 보내 한개의 cpu gpu detail 부분 요청 시
    @PostMapping("/find_cpu_detail_name")
    public CpuDetails findCpuName(@RequestBody String name) throws IOException {
        name = name.replace("+"," ").replace("=","");
        return cpuDetailsService.findByName(name);
    }
    @PostMapping("/find_cpu_name")
    public CpuList findCpuName2(@RequestBody String name) throws IOException {
        name = name.replace("+"," ").replace("=","");
        return cpuListService.findByName(name);
    }

    @PostMapping("/find_gpu_detail_name")
    public GpuDetails findGpuName(@RequestBody String name) throws IOException {
        name = name.replace("+"," ").replace("=","");
        return gpuDetailsService.findByName(name);
    }

    @PostMapping("/find_gpu_name")
    public GpuList findGpuName2(@RequestBody String name) throws IOException {
        name = name.replace("+"," ").replace("=","");
        return gpuListService.findByName(name);
    }

    // id를 보내 한개의 cpu gpu list 부분 요청 시
    @PostMapping("/find_cpu_id")
    public CpuList findCpuId(@RequestBody handleRequest id) throws IOException {
        return cpuListService.findById(id.getLastPart());
    }

    @PostMapping("/find_cpu_id2")
    public CpuList findCpuId(@RequestBody handleRequest2 id) throws IOException {
        return cpuListService.findById(id.getId());
    }


    @PostMapping("/find_gpu_id")
    public GpuList findGpuId(@RequestBody handleRequest id) throws IOException {
        return gpuListService.findById(id.getLastPart());
    }

    @PostMapping("/find_gpu_id2")
    public CpuList findGpuId(@RequestBody handleRequest2 id) throws IOException {
        return cpuListService.findById(id.getId());
    }

    // game 한개 선택 시 그 게임 이름 or id로 game origin 내용 보내기
    @PostMapping("/category/game1/detail")
    public GameListOrigin getSelectGameDetail(@RequestBody String game) {
        game = game.replace("+", " ");
        game = game.replace("=", "");
        return gameListOriginService.findByName(game);
    }
}