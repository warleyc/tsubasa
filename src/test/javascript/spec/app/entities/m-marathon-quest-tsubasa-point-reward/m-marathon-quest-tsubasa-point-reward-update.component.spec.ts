/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-marathon-quest-tsubasa-point-reward/m-marathon-quest-tsubasa-point-reward-update.component';
import { MMarathonQuestTsubasaPointRewardService } from 'app/entities/m-marathon-quest-tsubasa-point-reward/m-marathon-quest-tsubasa-point-reward.service';
import { MMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MMarathonQuestTsubasaPointReward Management Update Component', () => {
    let comp: MMarathonQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MMarathonQuestTsubasaPointRewardUpdateComponent>;
    let service: MMarathonQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonQuestTsubasaPointReward(123);
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
        const entity = new MMarathonQuestTsubasaPointReward();
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
