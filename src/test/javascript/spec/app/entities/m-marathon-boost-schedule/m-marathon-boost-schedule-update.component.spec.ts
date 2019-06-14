/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonBoostScheduleUpdateComponent } from 'app/entities/m-marathon-boost-schedule/m-marathon-boost-schedule-update.component';
import { MMarathonBoostScheduleService } from 'app/entities/m-marathon-boost-schedule/m-marathon-boost-schedule.service';
import { MMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';

describe('Component Tests', () => {
  describe('MMarathonBoostSchedule Management Update Component', () => {
    let comp: MMarathonBoostScheduleUpdateComponent;
    let fixture: ComponentFixture<MMarathonBoostScheduleUpdateComponent>;
    let service: MMarathonBoostScheduleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonBoostScheduleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonBoostScheduleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonBoostScheduleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonBoostScheduleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonBoostSchedule(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonBoostSchedule();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
