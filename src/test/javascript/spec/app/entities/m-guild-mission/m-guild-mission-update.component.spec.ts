/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildMissionUpdateComponent } from 'app/entities/m-guild-mission/m-guild-mission-update.component';
import { MGuildMissionService } from 'app/entities/m-guild-mission/m-guild-mission.service';
import { MGuildMission } from 'app/shared/model/m-guild-mission.model';

describe('Component Tests', () => {
  describe('MGuildMission Management Update Component', () => {
    let comp: MGuildMissionUpdateComponent;
    let fixture: ComponentFixture<MGuildMissionUpdateComponent>;
    let service: MGuildMissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildMissionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildMissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildMissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildMissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildMission(123);
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
        const entity = new MGuildMission();
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
