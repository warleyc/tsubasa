/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMissionRewardUpdateComponent } from 'app/entities/m-mission-reward/m-mission-reward-update.component';
import { MMissionRewardService } from 'app/entities/m-mission-reward/m-mission-reward.service';
import { MMissionReward } from 'app/shared/model/m-mission-reward.model';

describe('Component Tests', () => {
  describe('MMissionReward Management Update Component', () => {
    let comp: MMissionRewardUpdateComponent;
    let fixture: ComponentFixture<MMissionRewardUpdateComponent>;
    let service: MMissionRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMissionRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMissionRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMissionRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMissionRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMissionReward(123);
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
        const entity = new MMissionReward();
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
