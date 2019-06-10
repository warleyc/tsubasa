/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MContentableCardUpdateComponent } from 'app/entities/m-contentable-card/m-contentable-card-update.component';
import { MContentableCardService } from 'app/entities/m-contentable-card/m-contentable-card.service';
import { MContentableCard } from 'app/shared/model/m-contentable-card.model';

describe('Component Tests', () => {
  describe('MContentableCard Management Update Component', () => {
    let comp: MContentableCardUpdateComponent;
    let fixture: ComponentFixture<MContentableCardUpdateComponent>;
    let service: MContentableCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MContentableCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MContentableCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MContentableCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MContentableCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MContentableCard(123);
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
        const entity = new MContentableCard();
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
