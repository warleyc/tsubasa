/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSellCardMedalUpdateComponent } from 'app/entities/m-sell-card-medal/m-sell-card-medal-update.component';
import { MSellCardMedalService } from 'app/entities/m-sell-card-medal/m-sell-card-medal.service';
import { MSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';

describe('Component Tests', () => {
  describe('MSellCardMedal Management Update Component', () => {
    let comp: MSellCardMedalUpdateComponent;
    let fixture: ComponentFixture<MSellCardMedalUpdateComponent>;
    let service: MSellCardMedalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSellCardMedalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSellCardMedalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSellCardMedalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSellCardMedalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSellCardMedal(123);
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
        const entity = new MSellCardMedal();
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
