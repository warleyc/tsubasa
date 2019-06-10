/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCoopRoomStampUpdateComponent } from 'app/entities/m-coop-room-stamp/m-coop-room-stamp-update.component';
import { MCoopRoomStampService } from 'app/entities/m-coop-room-stamp/m-coop-room-stamp.service';
import { MCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';

describe('Component Tests', () => {
  describe('MCoopRoomStamp Management Update Component', () => {
    let comp: MCoopRoomStampUpdateComponent;
    let fixture: ComponentFixture<MCoopRoomStampUpdateComponent>;
    let service: MCoopRoomStampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCoopRoomStampUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCoopRoomStampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCoopRoomStampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCoopRoomStampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCoopRoomStamp(123);
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
        const entity = new MCoopRoomStamp();
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
