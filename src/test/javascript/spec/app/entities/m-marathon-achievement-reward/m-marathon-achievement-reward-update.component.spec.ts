/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonAchievementRewardUpdateComponent } from 'app/entities/m-marathon-achievement-reward/m-marathon-achievement-reward-update.component';
import { MMarathonAchievementRewardService } from 'app/entities/m-marathon-achievement-reward/m-marathon-achievement-reward.service';
import { MMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';

describe('Component Tests', () => {
  describe('MMarathonAchievementReward Management Update Component', () => {
    let comp: MMarathonAchievementRewardUpdateComponent;
    let fixture: ComponentFixture<MMarathonAchievementRewardUpdateComponent>;
    let service: MMarathonAchievementRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonAchievementRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonAchievementRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonAchievementRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonAchievementRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonAchievementReward(123);
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
        const entity = new MMarathonAchievementReward();
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
