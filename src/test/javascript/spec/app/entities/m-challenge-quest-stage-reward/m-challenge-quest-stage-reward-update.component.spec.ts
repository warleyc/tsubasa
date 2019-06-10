/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestStageRewardUpdateComponent } from 'app/entities/m-challenge-quest-stage-reward/m-challenge-quest-stage-reward-update.component';
import { MChallengeQuestStageRewardService } from 'app/entities/m-challenge-quest-stage-reward/m-challenge-quest-stage-reward.service';
import { MChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MChallengeQuestStageReward Management Update Component', () => {
    let comp: MChallengeQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MChallengeQuestStageRewardUpdateComponent>;
    let service: MChallengeQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MChallengeQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MChallengeQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MChallengeQuestStageReward(123);
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
        const entity = new MChallengeQuestStageReward();
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
