/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusIncentiveUpdateComponent } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive-update.component';
import { MLoginBonusIncentiveService } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive.service';
import { MLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

describe('Component Tests', () => {
  describe('MLoginBonusIncentive Management Update Component', () => {
    let comp: MLoginBonusIncentiveUpdateComponent;
    let fixture: ComponentFixture<MLoginBonusIncentiveUpdateComponent>;
    let service: MLoginBonusIncentiveService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusIncentiveUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLoginBonusIncentiveUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLoginBonusIncentiveUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLoginBonusIncentiveService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLoginBonusIncentive(123);
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
        const entity = new MLoginBonusIncentive();
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
