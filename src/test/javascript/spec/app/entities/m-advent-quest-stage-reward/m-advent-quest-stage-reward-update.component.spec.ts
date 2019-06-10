/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestStageRewardUpdateComponent } from 'app/entities/m-advent-quest-stage-reward/m-advent-quest-stage-reward-update.component';
import { MAdventQuestStageRewardService } from 'app/entities/m-advent-quest-stage-reward/m-advent-quest-stage-reward.service';
import { MAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MAdventQuestStageReward Management Update Component', () => {
    let comp: MAdventQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MAdventQuestStageRewardUpdateComponent>;
    let service: MAdventQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAdventQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAdventQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAdventQuestStageReward(123);
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
        const entity = new MAdventQuestStageReward();
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
