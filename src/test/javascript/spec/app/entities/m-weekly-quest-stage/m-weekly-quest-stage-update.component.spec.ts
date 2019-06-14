/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestStageUpdateComponent } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage-update.component';
import { MWeeklyQuestStageService } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage.service';
import { MWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestStage Management Update Component', () => {
    let comp: MWeeklyQuestStageUpdateComponent;
    let fixture: ComponentFixture<MWeeklyQuestStageUpdateComponent>;
    let service: MWeeklyQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MWeeklyQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MWeeklyQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MWeeklyQuestStage(123);
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
        const entity = new MWeeklyQuestStage();
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
