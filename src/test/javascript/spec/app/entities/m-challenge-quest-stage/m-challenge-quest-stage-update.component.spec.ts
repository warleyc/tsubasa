/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestStageUpdateComponent } from 'app/entities/m-challenge-quest-stage/m-challenge-quest-stage-update.component';
import { MChallengeQuestStageService } from 'app/entities/m-challenge-quest-stage/m-challenge-quest-stage.service';
import { MChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

describe('Component Tests', () => {
  describe('MChallengeQuestStage Management Update Component', () => {
    let comp: MChallengeQuestStageUpdateComponent;
    let fixture: ComponentFixture<MChallengeQuestStageUpdateComponent>;
    let service: MChallengeQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MChallengeQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MChallengeQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MChallengeQuestStage(123);
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
        const entity = new MChallengeQuestStage();
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
