/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildChatDefaultStampUpdateComponent } from 'app/entities/m-guild-chat-default-stamp/m-guild-chat-default-stamp-update.component';
import { MGuildChatDefaultStampService } from 'app/entities/m-guild-chat-default-stamp/m-guild-chat-default-stamp.service';
import { MGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';

describe('Component Tests', () => {
  describe('MGuildChatDefaultStamp Management Update Component', () => {
    let comp: MGuildChatDefaultStampUpdateComponent;
    let fixture: ComponentFixture<MGuildChatDefaultStampUpdateComponent>;
    let service: MGuildChatDefaultStampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildChatDefaultStampUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildChatDefaultStampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildChatDefaultStampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildChatDefaultStampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildChatDefaultStamp(123);
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
        const entity = new MGuildChatDefaultStamp();
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
