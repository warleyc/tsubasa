/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestStageUpdateComponent } from 'app/entities/m-luck-weekly-quest-stage/m-luck-weekly-quest-stage-update.component';
import { MLuckWeeklyQuestStageService } from 'app/entities/m-luck-weekly-quest-stage/m-luck-weekly-quest-stage.service';
import { MLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestStage Management Update Component', () => {
    let comp: MLuckWeeklyQuestStageUpdateComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestStageUpdateComponent>;
    let service: MLuckWeeklyQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLuckWeeklyQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLuckWeeklyQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLuckWeeklyQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLuckWeeklyQuestStage(123);
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
        const entity = new MLuckWeeklyQuestStage();
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
