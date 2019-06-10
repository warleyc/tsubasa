/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardGroupUpdateComponent } from 'app/entities/m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group-update.component';
import { MChallengeQuestAchievementRewardGroupService } from 'app/entities/m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group.service';
import { MChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementRewardGroup Management Update Component', () => {
    let comp: MChallengeQuestAchievementRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardGroupUpdateComponent>;
    let service: MChallengeQuestAchievementRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestAchievementRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MChallengeQuestAchievementRewardGroup(123);
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
        const entity = new MChallengeQuestAchievementRewardGroup();
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
