/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpPlayerStampUpdateComponent } from 'app/entities/m-pvp-player-stamp/m-pvp-player-stamp-update.component';
import { MPvpPlayerStampService } from 'app/entities/m-pvp-player-stamp/m-pvp-player-stamp.service';
import { MPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';

describe('Component Tests', () => {
  describe('MPvpPlayerStamp Management Update Component', () => {
    let comp: MPvpPlayerStampUpdateComponent;
    let fixture: ComponentFixture<MPvpPlayerStampUpdateComponent>;
    let service: MPvpPlayerStampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpPlayerStampUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpPlayerStampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpPlayerStampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpPlayerStampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpPlayerStamp(123);
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
        const entity = new MPvpPlayerStamp();
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
