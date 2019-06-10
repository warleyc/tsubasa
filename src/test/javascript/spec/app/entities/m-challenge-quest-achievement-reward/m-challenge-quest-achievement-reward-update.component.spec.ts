/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardUpdateComponent } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward-update.component';
import { MChallengeQuestAchievementRewardService } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward.service';
import { MChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementReward Management Update Component', () => {
    let comp: MChallengeQuestAchievementRewardUpdateComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardUpdateComponent>;
    let service: MChallengeQuestAchievementRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestAchievementRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MChallengeQuestAchievementReward(123);
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
        const entity = new MChallengeQuestAchievementReward();
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
