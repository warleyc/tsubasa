/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonAchievementRewardGroupUpdateComponent } from 'app/entities/m-marathon-achievement-reward-group/m-marathon-achievement-reward-group-update.component';
import { MMarathonAchievementRewardGroupService } from 'app/entities/m-marathon-achievement-reward-group/m-marathon-achievement-reward-group.service';
import { MMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonAchievementRewardGroup Management Update Component', () => {
    let comp: MMarathonAchievementRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MMarathonAchievementRewardGroupUpdateComponent>;
    let service: MMarathonAchievementRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonAchievementRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonAchievementRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonAchievementRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonAchievementRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonAchievementRewardGroup(123);
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
        const entity = new MMarathonAchievementRewardGroup();
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
