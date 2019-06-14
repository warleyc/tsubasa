/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestStageUpdateComponent } from 'app/entities/m-guerilla-quest-stage/m-guerilla-quest-stage-update.component';
import { MGuerillaQuestStageService } from 'app/entities/m-guerilla-quest-stage/m-guerilla-quest-stage.service';
import { MGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestStage Management Update Component', () => {
    let comp: MGuerillaQuestStageUpdateComponent;
    let fixture: ComponentFixture<MGuerillaQuestStageUpdateComponent>;
    let service: MGuerillaQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuerillaQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuerillaQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuerillaQuestStage(123);
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
        const entity = new MGuerillaQuestStage();
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
