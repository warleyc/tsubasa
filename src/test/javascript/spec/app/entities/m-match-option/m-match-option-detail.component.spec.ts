/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchOptionDetailComponent } from 'app/entities/m-match-option/m-match-option-detail.component';
import { MMatchOption } from 'app/shared/model/m-match-option.model';

describe('Component Tests', () => {
  describe('MMatchOption Management Detail Component', () => {
    let comp: MMatchOptionDetailComponent;
    let fixture: ComponentFixture<MMatchOptionDetailComponent>;
    const route = ({ data: of({ mMatchOption: new MMatchOption(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchOptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMatchOptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchOptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMatchOption).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
